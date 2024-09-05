import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITimeKeeping } from 'app/shared/model/time-keeping.model';
import { getEntities, reset } from './time-keeping.reducer';

export const TimeKeeping = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const timeKeepingList = useAppSelector(state => state.timeKeeping.entities);
  const loading = useAppSelector(state => state.timeKeeping.loading);
  const totalItems = useAppSelector(state => state.timeKeeping.totalItems);
  const links = useAppSelector(state => state.timeKeeping.links);
  const entity = useAppSelector(state => state.timeKeeping.entity);
  const updateSuccess = useAppSelector(state => state.timeKeeping.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  return (
    <div>
      <h2 id="time-keeping-heading" data-cy="TimeKeepingHeading">
        Time Keepings
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/time-keeping/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Thêm mới một Time Keeping
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={timeKeepingList ? timeKeepingList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {timeKeepingList && timeKeepingList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    ID <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('date')}>
                    Date <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('checkIn')}>
                    Check In <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('checkOut')}>
                    Check Out <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Employee <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {timeKeepingList.map((timeKeeping, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/time-keeping/${timeKeeping.id}`} color="link" size="sm">
                        {timeKeeping.id}
                      </Button>
                    </td>
                    <td>{timeKeeping.date ? <TextFormat type="date" value={timeKeeping.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                    <td>{timeKeeping.checkIn ? <TextFormat type="date" value={timeKeeping.checkIn} format={APP_DATE_FORMAT} /> : null}</td>
                    <td>
                      {timeKeeping.checkOut ? <TextFormat type="date" value={timeKeeping.checkOut} format={APP_DATE_FORMAT} /> : null}
                    </td>
                    <td>
                      {timeKeeping.employee ? (
                        <Link to={`/employee/${timeKeeping.employee.id}`}>{timeKeeping.employee.employeeName}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/time-keeping/${timeKeeping.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Xem</span>
                        </Button>
                        <Button tag={Link} to={`/time-keeping/${timeKeeping.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`/time-keeping/${timeKeeping.id}/delete`}
                          color="danger"
                          size="sm"
                          data-cy="entityDeleteButton"
                        >
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Xóa</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && <div className="alert alert-warning">No Time Keepings found</div>
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default TimeKeeping;

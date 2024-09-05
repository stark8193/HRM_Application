import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities, reset } from './employee.reducer';

export const Employee = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const employeeList = useAppSelector(state => state.employee.entities);
  const loading = useAppSelector(state => state.employee.loading);
  const totalItems = useAppSelector(state => state.employee.totalItems);
  const links = useAppSelector(state => state.employee.links);
  const entity = useAppSelector(state => state.employee.entity);
  const updateSuccess = useAppSelector(state => state.employee.updateSuccess);

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
      <h2 id="employee-heading" data-cy="EmployeeHeading">
        Employees
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/employee/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Thêm mới một Employee
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={employeeList ? employeeList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {employeeList && employeeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    ID <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('employeeName')}>
                    Employee Name <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('birthDate')}>
                    Birth Date <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('gender')}>
                    Gender <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('hireDate')}>
                    Hire Date <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('email')}>
                    Email <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('phone')}>
                    Phone <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('employeeStatus')}>
                    Employee Status <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('taxCode')}>
                    Tax Code <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('cccd')}>
                    Cccd <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('address')}>
                    Address <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('bankAccountNumber')}>
                    Bank Account Number <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('bank')}>
                    Bank <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('bankBranch')}>
                    Bank Branch <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('photoPath')}>
                    Photo Path <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    User <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Job <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Department <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {employeeList.map((employee, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/employee/${employee.id}`} color="link" size="sm">
                        {employee.id}
                      </Button>
                    </td>
                    <td>{employee.employeeName}</td>
                    <td>
                      {employee.birthDate ? <TextFormat type="date" value={employee.birthDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{employee.gender}</td>
                    <td>
                      {employee.hireDate ? <TextFormat type="date" value={employee.hireDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{employee.email}</td>
                    <td>{employee.phone}</td>
                    <td>{employee.employeeStatus}</td>
                    <td>{employee.taxCode}</td>
                    <td>{employee.cccd}</td>
                    <td>{employee.address}</td>
                    <td>{employee.bankAccountNumber}</td>
                    <td>{employee.bank}</td>
                    <td>{employee.bankBranch}</td>
                    <td>
                      {employee.photoPath ? (
                        <div>
                          {employee.photoPathContentType ? (
                            <a onClick={openFile(employee.photoPathContentType, employee.photoPath)}>
                              <img
                                src={`data:${employee.photoPathContentType};base64,${employee.photoPath}`}
                                style={{ maxHeight: '30px' }}
                              />
                              &nbsp;
                            </a>
                          ) : null}
                          <span>
                            {employee.photoPathContentType}, {byteSize(employee.photoPath)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>{employee.user ? employee.user.login : ''}</td>
                    <td>{employee.job ? <Link to={`/job/${employee.job.id}`}>{employee.job.jobName}</Link> : ''}</td>
                    <td>
                      {employee.department ? (
                        <Link to={`/department/${employee.department.id}`}>{employee.department.departName}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/employee/${employee.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Xem</span>
                        </Button>
                        <Button tag={Link} to={`/employee/${employee.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
                        </Button>
                        <Button tag={Link} to={`/employee/${employee.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Xóa</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && <div className="alert alert-warning">No Employees found</div>
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default Employee;

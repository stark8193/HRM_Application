import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './time-keeping.reducer';

export const TimeKeepingDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const timeKeepingEntity = useAppSelector(state => state.timeKeeping.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="timeKeepingDetailsHeading">Time Keeping</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{timeKeepingEntity.id}</dd>
          <dt>
            <span id="date">Date</span>
          </dt>
          <dd>
            {timeKeepingEntity.date ? <TextFormat value={timeKeepingEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="checkIn">Check In</span>
          </dt>
          <dd>
            {timeKeepingEntity.checkIn ? <TextFormat value={timeKeepingEntity.checkIn} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="checkOut">Check Out</span>
          </dt>
          <dd>
            {timeKeepingEntity.checkOut ? <TextFormat value={timeKeepingEntity.checkOut} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>Employee</dt>
          <dd>{timeKeepingEntity.employee ? timeKeepingEntity.employee.employeeName : ''}</dd>
        </dl>
        <Button tag={Link} to="/time-keeping" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Quay lại</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/time-keeping/${timeKeepingEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TimeKeepingDetail;

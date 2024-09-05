import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './leave-request.reducer';

export const LeaveRequestDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const leaveRequestEntity = useAppSelector(state => state.leaveRequest.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="leaveRequestDetailsHeading">Leave Request</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{leaveRequestEntity.id}</dd>
          <dt>
            <span id="startDate">Start Date</span>
          </dt>
          <dd>
            {leaveRequestEntity.startDate ? (
              <TextFormat value={leaveRequestEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endDate">End Date</span>
          </dt>
          <dd>
            {leaveRequestEntity.endDate ? (
              <TextFormat value={leaveRequestEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{leaveRequestEntity.status}</dd>
          <dt>
            <span id="reason">Reason</span>
          </dt>
          <dd>{leaveRequestEntity.reason}</dd>
          <dt>Employee</dt>
          <dd>{leaveRequestEntity.employee ? leaveRequestEntity.employee.employeeName : ''}</dd>
          <dt>Leave Type</dt>
          <dd>{leaveRequestEntity.leaveType ? leaveRequestEntity.leaveType.leaveTypeName : ''}</dd>
        </dl>
        <Button tag={Link} to="/leave-request" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Quay lại</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/leave-request/${leaveRequestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
        </Button>
      </Col>
    </Row>
  );
};

export default LeaveRequestDetail;

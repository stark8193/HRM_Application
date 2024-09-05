import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './leave-type.reducer';

export const LeaveTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const leaveTypeEntity = useAppSelector(state => state.leaveType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="leaveTypeDetailsHeading">Leave Type</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{leaveTypeEntity.id}</dd>
          <dt>
            <span id="leaveTypeName">Leave Type Name</span>
          </dt>
          <dd>{leaveTypeEntity.leaveTypeName}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{leaveTypeEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/leave-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Quay lại</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/leave-type/${leaveTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
        </Button>
      </Col>
    </Row>
  );
};

export default LeaveTypeDetail;

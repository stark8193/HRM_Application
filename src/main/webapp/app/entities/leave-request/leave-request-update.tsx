import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { ILeaveType } from 'app/shared/model/leave-type.model';
import { getEntities as getLeaveTypes } from 'app/entities/leave-type/leave-type.reducer';
import { ILeaveRequest } from 'app/shared/model/leave-request.model';
import { LeaveRequestStatus } from 'app/shared/model/enumerations/leave-request-status.model';
import { getEntity, updateEntity, createEntity, reset } from './leave-request.reducer';

export const LeaveRequestUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const employees = useAppSelector(state => state.employee.entities);
  const leaveTypes = useAppSelector(state => state.leaveType.entities);
  const leaveRequestEntity = useAppSelector(state => state.leaveRequest.entity);
  const loading = useAppSelector(state => state.leaveRequest.loading);
  const updating = useAppSelector(state => state.leaveRequest.updating);
  const updateSuccess = useAppSelector(state => state.leaveRequest.updateSuccess);
  const leaveRequestStatusValues = Object.keys(LeaveRequestStatus);

  const handleClose = () => {
    navigate('/leave-request');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getEmployees({}));
    dispatch(getLeaveTypes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...leaveRequestEntity,
      ...values,
      employee: employees.find(it => it.id.toString() === values.employee.toString()),
      leaveType: leaveTypes.find(it => it.id.toString() === values.leaveType.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          status: 'PENDING',
          ...leaveRequestEntity,
          employee: leaveRequestEntity?.employee?.id,
          leaveType: leaveRequestEntity?.leaveType?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="hrmApplicationApp.leaveRequest.home.createOrEditLabel" data-cy="LeaveRequestCreateUpdateHeading">
            Thêm mới hoặc cập nhật Leave Request
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="leave-request-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField
                label="Start Date"
                id="leave-request-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField
                label="End Date"
                id="leave-request-endDate"
                name="endDate"
                data-cy="endDate"
                type="date"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField label="Status" id="leave-request-status" name="status" data-cy="status" type="select">
                {leaveRequestStatusValues.map(leaveRequestStatus => (
                  <option value={leaveRequestStatus} key={leaveRequestStatus}>
                    {leaveRequestStatus}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Reason" id="leave-request-reason" name="reason" data-cy="reason" type="text" />
              <ValidatedField id="leave-request-employee" name="employee" data-cy="employee" label="Employee" type="select">
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.employeeName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="leave-request-leaveType" name="leaveType" data-cy="leaveType" label="Leave Type" type="select">
                <option value="" key="0" />
                {leaveTypes
                  ? leaveTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.leaveTypeName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/leave-request" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Quay lại</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Lưu
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default LeaveRequestUpdate;

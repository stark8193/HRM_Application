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
import { ITimeKeeping } from 'app/shared/model/time-keeping.model';
import { getEntity, updateEntity, createEntity, reset } from './time-keeping.reducer';

export const TimeKeepingUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const employees = useAppSelector(state => state.employee.entities);
  const timeKeepingEntity = useAppSelector(state => state.timeKeeping.entity);
  const loading = useAppSelector(state => state.timeKeeping.loading);
  const updating = useAppSelector(state => state.timeKeeping.updating);
  const updateSuccess = useAppSelector(state => state.timeKeeping.updateSuccess);

  const handleClose = () => {
    navigate('/time-keeping');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getEmployees({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.checkIn = convertDateTimeToServer(values.checkIn);
    values.checkOut = convertDateTimeToServer(values.checkOut);

    const entity = {
      ...timeKeepingEntity,
      ...values,
      employee: employees.find(it => it.id.toString() === values.employee.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          checkIn: displayDefaultDateTime(),
          checkOut: displayDefaultDateTime(),
        }
      : {
          ...timeKeepingEntity,
          checkIn: convertDateTimeFromServer(timeKeepingEntity.checkIn),
          checkOut: convertDateTimeFromServer(timeKeepingEntity.checkOut),
          employee: timeKeepingEntity?.employee?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="hrmApplicationApp.timeKeeping.home.createOrEditLabel" data-cy="TimeKeepingCreateUpdateHeading">
            Thêm mới hoặc cập nhật Time Keeping
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="time-keeping-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Date"
                id="time-keeping-date"
                name="date"
                data-cy="date"
                type="date"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField
                label="Check In"
                id="time-keeping-checkIn"
                name="checkIn"
                data-cy="checkIn"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField
                label="Check Out"
                id="time-keeping-checkOut"
                name="checkOut"
                data-cy="checkOut"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField id="time-keeping-employee" name="employee" data-cy="employee" label="Employee" type="select">
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.employeeName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/time-keeping" replace color="info">
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

export default TimeKeepingUpdate;

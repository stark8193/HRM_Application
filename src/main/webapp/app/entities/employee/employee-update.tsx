import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IJob } from 'app/shared/model/job.model';
import { getEntities as getJobs } from 'app/entities/job/job.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { EmpStatus } from 'app/shared/model/enumerations/emp-status.model';
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';

export const EmployeeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const jobs = useAppSelector(state => state.job.entities);
  const departments = useAppSelector(state => state.department.entities);
  const employeeEntity = useAppSelector(state => state.employee.entity);
  const loading = useAppSelector(state => state.employee.loading);
  const updating = useAppSelector(state => state.employee.updating);
  const updateSuccess = useAppSelector(state => state.employee.updateSuccess);
  const genderValues = Object.keys(Gender);
  const empStatusValues = Object.keys(EmpStatus);

  const handleClose = () => {
    navigate('/employee');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
    dispatch(getJobs({}));
    dispatch(getDepartments({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...employeeEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
      job: jobs.find(it => it.id.toString() === values.job.toString()),
      department: departments.find(it => it.id.toString() === values.department.toString()),
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
          gender: 'MALE',
          employeeStatus: 'FULLTIME',
          ...employeeEntity,
          user: employeeEntity?.user?.id,
          job: employeeEntity?.job?.id,
          department: employeeEntity?.department?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="hrmApplicationApp.employee.home.createOrEditLabel" data-cy="EmployeeCreateUpdateHeading">
            Thêm mới hoặc cập nhật Employee
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="employee-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Employee Name"
                id="employee-employeeName"
                name="employeeName"
                data-cy="employeeName"
                type="text"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField
                label="Birth Date"
                id="employee-birthDate"
                name="birthDate"
                data-cy="birthDate"
                type="date"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField label="Gender" id="employee-gender" name="gender" data-cy="gender" type="select">
                {genderValues.map(gender => (
                  <option value={gender} key={gender}>
                    {gender}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Hire Date"
                id="employee-hireDate"
                name="hireDate"
                data-cy="hireDate"
                type="date"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                }}
              />
              <ValidatedField label="Email" id="employee-email" name="email" data-cy="email" type="text" />
              <ValidatedField
                label="Phone"
                id="employee-phone"
                name="phone"
                data-cy="phone"
                type="text"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                  validate: v => isNumber(v) || 'Trường này phải là một số.',
                }}
              />
              <ValidatedField
                label="Employee Status"
                id="employee-employeeStatus"
                name="employeeStatus"
                data-cy="employeeStatus"
                type="select"
              >
                {empStatusValues.map(empStatus => (
                  <option value={empStatus} key={empStatus}>
                    {empStatus}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Tax Code"
                id="employee-taxCode"
                name="taxCode"
                data-cy="taxCode"
                type="text"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                  validate: v => isNumber(v) || 'Trường này phải là một số.',
                }}
              />
              <ValidatedField
                label="Cccd"
                id="employee-cccd"
                name="cccd"
                data-cy="cccd"
                type="text"
                validate={{
                  required: { value: true, message: 'Trường này bắt buộc phải nhập.' },
                  validate: v => isNumber(v) || 'Trường này phải là một số.',
                }}
              />
              <ValidatedField label="Address" id="employee-address" name="address" data-cy="address" type="text" />
              <ValidatedField
                label="Bank Account Number"
                id="employee-bankAccountNumber"
                name="bankAccountNumber"
                data-cy="bankAccountNumber"
                type="text"
              />
              <ValidatedField label="Bank" id="employee-bank" name="bank" data-cy="bank" type="text" />
              <ValidatedField label="Bank Branch" id="employee-bankBranch" name="bankBranch" data-cy="bankBranch" type="text" />
              <ValidatedBlobField
                label="Photo Path"
                id="employee-photoPath"
                name="photoPath"
                data-cy="photoPath"
                isImage
                accept="image/*"
              />
              <ValidatedField id="employee-user" name="user" data-cy="user" label="User" type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="employee-job" name="job" data-cy="job" label="Job" type="select">
                <option value="" key="0" />
                {jobs
                  ? jobs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.jobName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="employee-department" name="department" data-cy="department" label="Department" type="select">
                <option value="" key="0" />
                {departments
                  ? departments.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.departName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/employee" replace color="info">
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

export default EmployeeUpdate;

import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './employee.reducer';

export const EmployeeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const employeeEntity = useAppSelector(state => state.employee.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="employeeDetailsHeading">Employee</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{employeeEntity.id}</dd>
          <dt>
            <span id="employeeName">Employee Name</span>
          </dt>
          <dd>{employeeEntity.employeeName}</dd>
          <dt>
            <span id="birthDate">Birth Date</span>
          </dt>
          <dd>
            {employeeEntity.birthDate ? <TextFormat value={employeeEntity.birthDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="gender">Gender</span>
          </dt>
          <dd>{employeeEntity.gender}</dd>
          <dt>
            <span id="hireDate">Hire Date</span>
          </dt>
          <dd>
            {employeeEntity.hireDate ? <TextFormat value={employeeEntity.hireDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{employeeEntity.email}</dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{employeeEntity.phone}</dd>
          <dt>
            <span id="employeeStatus">Employee Status</span>
          </dt>
          <dd>{employeeEntity.employeeStatus}</dd>
          <dt>
            <span id="taxCode">Tax Code</span>
          </dt>
          <dd>{employeeEntity.taxCode}</dd>
          <dt>
            <span id="cccd">Cccd</span>
          </dt>
          <dd>{employeeEntity.cccd}</dd>
          <dt>
            <span id="address">Address</span>
          </dt>
          <dd>{employeeEntity.address}</dd>
          <dt>
            <span id="bankAccountNumber">Bank Account Number</span>
          </dt>
          <dd>{employeeEntity.bankAccountNumber}</dd>
          <dt>
            <span id="bank">Bank</span>
          </dt>
          <dd>{employeeEntity.bank}</dd>
          <dt>
            <span id="bankBranch">Bank Branch</span>
          </dt>
          <dd>{employeeEntity.bankBranch}</dd>
          <dt>
            <span id="photoPath">Photo Path</span>
          </dt>
          <dd>
            {employeeEntity.photoPath ? (
              <div>
                {employeeEntity.photoPathContentType ? (
                  <a onClick={openFile(employeeEntity.photoPathContentType, employeeEntity.photoPath)}>
                    <img
                      src={`data:${employeeEntity.photoPathContentType};base64,${employeeEntity.photoPath}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {employeeEntity.photoPathContentType}, {byteSize(employeeEntity.photoPath)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>User</dt>
          <dd>{employeeEntity.user ? employeeEntity.user.login : ''}</dd>
          <dt>Job</dt>
          <dd>{employeeEntity.job ? employeeEntity.job.jobName : ''}</dd>
          <dt>Department</dt>
          <dd>{employeeEntity.department ? employeeEntity.department.departName : ''}</dd>
        </dl>
        <Button tag={Link} to="/employee" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Quay lại</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employee/${employeeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Sửa</span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmployeeDetail;

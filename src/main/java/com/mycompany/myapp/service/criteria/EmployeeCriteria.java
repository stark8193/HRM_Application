package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.EmpStatus;
import com.mycompany.myapp.domain.enumeration.Gender;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Employee} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EmployeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmployeeCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {

        public GenderFilter() {}

        public GenderFilter(GenderFilter filter) {
            super(filter);
        }

        @Override
        public GenderFilter copy() {
            return new GenderFilter(this);
        }
    }

    /**
     * Class for filtering EmpStatus
     */
    public static class EmpStatusFilter extends Filter<EmpStatus> {

        public EmpStatusFilter() {}

        public EmpStatusFilter(EmpStatusFilter filter) {
            super(filter);
        }

        @Override
        public EmpStatusFilter copy() {
            return new EmpStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter employeeName;

    private LocalDateFilter birthDate;

    private GenderFilter gender;

    private LocalDateFilter hireDate;

    private StringFilter email;

    private IntegerFilter phone;

    private EmpStatusFilter employeeStatus;

    private IntegerFilter taxCode;

    private IntegerFilter cccd;

    private StringFilter address;

    private IntegerFilter bankAccountNumber;

    private StringFilter bank;

    private StringFilter bankBranch;

    private LongFilter userId;

    private LongFilter jobId;

    private LongFilter timeKeepingId;

    private LongFilter leaveRequestId;

    private LongFilter departmentId;

    private Boolean distinct;

    public EmployeeCriteria() {}

    public EmployeeCriteria(EmployeeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.employeeName = other.employeeName == null ? null : other.employeeName.copy();
        this.birthDate = other.birthDate == null ? null : other.birthDate.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.hireDate = other.hireDate == null ? null : other.hireDate.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.employeeStatus = other.employeeStatus == null ? null : other.employeeStatus.copy();
        this.taxCode = other.taxCode == null ? null : other.taxCode.copy();
        this.cccd = other.cccd == null ? null : other.cccd.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.bankAccountNumber = other.bankAccountNumber == null ? null : other.bankAccountNumber.copy();
        this.bank = other.bank == null ? null : other.bank.copy();
        this.bankBranch = other.bankBranch == null ? null : other.bankBranch.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.jobId = other.jobId == null ? null : other.jobId.copy();
        this.timeKeepingId = other.timeKeepingId == null ? null : other.timeKeepingId.copy();
        this.leaveRequestId = other.leaveRequestId == null ? null : other.leaveRequestId.copy();
        this.departmentId = other.departmentId == null ? null : other.departmentId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EmployeeCriteria copy() {
        return new EmployeeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEmployeeName() {
        return employeeName;
    }

    public StringFilter employeeName() {
        if (employeeName == null) {
            employeeName = new StringFilter();
        }
        return employeeName;
    }

    public void setEmployeeName(StringFilter employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateFilter getBirthDate() {
        return birthDate;
    }

    public LocalDateFilter birthDate() {
        if (birthDate == null) {
            birthDate = new LocalDateFilter();
        }
        return birthDate;
    }

    public void setBirthDate(LocalDateFilter birthDate) {
        this.birthDate = birthDate;
    }

    public GenderFilter getGender() {
        return gender;
    }

    public GenderFilter gender() {
        if (gender == null) {
            gender = new GenderFilter();
        }
        return gender;
    }

    public void setGender(GenderFilter gender) {
        this.gender = gender;
    }

    public LocalDateFilter getHireDate() {
        return hireDate;
    }

    public LocalDateFilter hireDate() {
        if (hireDate == null) {
            hireDate = new LocalDateFilter();
        }
        return hireDate;
    }

    public void setHireDate(LocalDateFilter hireDate) {
        this.hireDate = hireDate;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public IntegerFilter getPhone() {
        return phone;
    }

    public IntegerFilter phone() {
        if (phone == null) {
            phone = new IntegerFilter();
        }
        return phone;
    }

    public void setPhone(IntegerFilter phone) {
        this.phone = phone;
    }

    public EmpStatusFilter getEmployeeStatus() {
        return employeeStatus;
    }

    public EmpStatusFilter employeeStatus() {
        if (employeeStatus == null) {
            employeeStatus = new EmpStatusFilter();
        }
        return employeeStatus;
    }

    public void setEmployeeStatus(EmpStatusFilter employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public IntegerFilter getTaxCode() {
        return taxCode;
    }

    public IntegerFilter taxCode() {
        if (taxCode == null) {
            taxCode = new IntegerFilter();
        }
        return taxCode;
    }

    public void setTaxCode(IntegerFilter taxCode) {
        this.taxCode = taxCode;
    }

    public IntegerFilter getCccd() {
        return cccd;
    }

    public IntegerFilter cccd() {
        if (cccd == null) {
            cccd = new IntegerFilter();
        }
        return cccd;
    }

    public void setCccd(IntegerFilter cccd) {
        this.cccd = cccd;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public IntegerFilter getBankAccountNumber() {
        return bankAccountNumber;
    }

    public IntegerFilter bankAccountNumber() {
        if (bankAccountNumber == null) {
            bankAccountNumber = new IntegerFilter();
        }
        return bankAccountNumber;
    }

    public void setBankAccountNumber(IntegerFilter bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public StringFilter getBank() {
        return bank;
    }

    public StringFilter bank() {
        if (bank == null) {
            bank = new StringFilter();
        }
        return bank;
    }

    public void setBank(StringFilter bank) {
        this.bank = bank;
    }

    public StringFilter getBankBranch() {
        return bankBranch;
    }

    public StringFilter bankBranch() {
        if (bankBranch == null) {
            bankBranch = new StringFilter();
        }
        return bankBranch;
    }

    public void setBankBranch(StringFilter bankBranch) {
        this.bankBranch = bankBranch;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getJobId() {
        return jobId;
    }

    public LongFilter jobId() {
        if (jobId == null) {
            jobId = new LongFilter();
        }
        return jobId;
    }

    public void setJobId(LongFilter jobId) {
        this.jobId = jobId;
    }

    public LongFilter getTimeKeepingId() {
        return timeKeepingId;
    }

    public LongFilter timeKeepingId() {
        if (timeKeepingId == null) {
            timeKeepingId = new LongFilter();
        }
        return timeKeepingId;
    }

    public void setTimeKeepingId(LongFilter timeKeepingId) {
        this.timeKeepingId = timeKeepingId;
    }

    public LongFilter getLeaveRequestId() {
        return leaveRequestId;
    }

    public LongFilter leaveRequestId() {
        if (leaveRequestId == null) {
            leaveRequestId = new LongFilter();
        }
        return leaveRequestId;
    }

    public void setLeaveRequestId(LongFilter leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public LongFilter getDepartmentId() {
        return departmentId;
    }

    public LongFilter departmentId() {
        if (departmentId == null) {
            departmentId = new LongFilter();
        }
        return departmentId;
    }

    public void setDepartmentId(LongFilter departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeeCriteria that = (EmployeeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(employeeName, that.employeeName) &&
            Objects.equals(birthDate, that.birthDate) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(hireDate, that.hireDate) &&
            Objects.equals(email, that.email) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(employeeStatus, that.employeeStatus) &&
            Objects.equals(taxCode, that.taxCode) &&
            Objects.equals(cccd, that.cccd) &&
            Objects.equals(address, that.address) &&
            Objects.equals(bankAccountNumber, that.bankAccountNumber) &&
            Objects.equals(bank, that.bank) &&
            Objects.equals(bankBranch, that.bankBranch) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(timeKeepingId, that.timeKeepingId) &&
            Objects.equals(leaveRequestId, that.leaveRequestId) &&
            Objects.equals(departmentId, that.departmentId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            employeeName,
            birthDate,
            gender,
            hireDate,
            email,
            phone,
            employeeStatus,
            taxCode,
            cccd,
            address,
            bankAccountNumber,
            bank,
            bankBranch,
            userId,
            jobId,
            timeKeepingId,
            leaveRequestId,
            departmentId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (employeeName != null ? "employeeName=" + employeeName + ", " : "") +
            (birthDate != null ? "birthDate=" + birthDate + ", " : "") +
            (gender != null ? "gender=" + gender + ", " : "") +
            (hireDate != null ? "hireDate=" + hireDate + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (phone != null ? "phone=" + phone + ", " : "") +
            (employeeStatus != null ? "employeeStatus=" + employeeStatus + ", " : "") +
            (taxCode != null ? "taxCode=" + taxCode + ", " : "") +
            (cccd != null ? "cccd=" + cccd + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (bankAccountNumber != null ? "bankAccountNumber=" + bankAccountNumber + ", " : "") +
            (bank != null ? "bank=" + bank + ", " : "") +
            (bankBranch != null ? "bankBranch=" + bankBranch + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (jobId != null ? "jobId=" + jobId + ", " : "") +
            (timeKeepingId != null ? "timeKeepingId=" + timeKeepingId + ", " : "") +
            (leaveRequestId != null ? "leaveRequestId=" + leaveRequestId + ", " : "") +
            (departmentId != null ? "departmentId=" + departmentId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

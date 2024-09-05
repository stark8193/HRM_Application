package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.EmpStatus;
import com.mycompany.myapp.domain.enumeration.Gender;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Employee} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull
    private String employeeName;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate hireDate;

    private String email;

    @NotNull
    private Integer phone;

    @NotNull
    private EmpStatus employeeStatus;

    @NotNull
    private Integer taxCode;

    @NotNull
    private Integer cccd;

    private String address;

    private Integer bankAccountNumber;

    private String bank;

    private String bankBranch;

    @Lob
    private byte[] photoPath;

    private String photoPathContentType;
    private UserDTO user;

    private JobDTO job;

    private DepartmentDTO department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public EmpStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmpStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Integer getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }

    public Integer getCccd() {
        return cccd;
    }

    public void setCccd(Integer cccd) {
        this.cccd = cccd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Integer bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public byte[] getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(byte[] photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPathContentType() {
        return photoPathContentType;
    }

    public void setPhotoPathContentType(String photoPathContentType) {
        this.photoPathContentType = photoPathContentType;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public JobDTO getJob() {
        return job;
    }

    public void setJob(JobDTO job) {
        this.job = job;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, employeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", employeeName='" + getEmployeeName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", gender='" + getGender() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            ", employeeStatus='" + getEmployeeStatus() + "'" +
            ", taxCode=" + getTaxCode() +
            ", cccd=" + getCccd() +
            ", address='" + getAddress() + "'" +
            ", bankAccountNumber=" + getBankAccountNumber() +
            ", bank='" + getBank() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", photoPath='" + getPhotoPath() + "'" +
            ", user=" + getUser() +
            ", job=" + getJob() +
            ", department=" + getDepartment() +
            "}";
    }
}

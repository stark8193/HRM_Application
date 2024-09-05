package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.EmpStatus;
import com.mycompany.myapp.domain.enumeration.Gender;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone", nullable = false)
    private Integer phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status", nullable = false)
    private EmpStatus employeeStatus;

    @NotNull
    @Column(name = "tax_code", nullable = false)
    private Integer taxCode;

    @NotNull
    @Column(name = "cccd", nullable = false)
    private Integer cccd;

    @Column(name = "address")
    private String address;

    @Column(name = "bank_account_number")
    private Integer bankAccountNumber;

    @Column(name = "bank")
    private String bank;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Lob
    @Column(name = "photo_path")
    private byte[] photoPath;

    @Column(name = "photo_path_content_type")
    private String photoPathContentType;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @JsonIgnoreProperties(value = { "employee" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Job job;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "employee" }, allowSetters = true)
    private Set<TimeKeeping> timeKeepings = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "employee", "leaveType" }, allowSetters = true)
    private Set<LeaveRequest> leaveRequests = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public Employee employeeName(String employeeName) {
        this.setEmployeeName(employeeName);
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Employee birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Employee gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return this.hireDate;
    }

    public Employee hireDate(LocalDate hireDate) {
        this.setHireDate(hireDate);
        return this;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmail() {
        return this.email;
    }

    public Employee email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public Employee phone(Integer phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public EmpStatus getEmployeeStatus() {
        return this.employeeStatus;
    }

    public Employee employeeStatus(EmpStatus employeeStatus) {
        this.setEmployeeStatus(employeeStatus);
        return this;
    }

    public void setEmployeeStatus(EmpStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Integer getTaxCode() {
        return this.taxCode;
    }

    public Employee taxCode(Integer taxCode) {
        this.setTaxCode(taxCode);
        return this;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }

    public Integer getCccd() {
        return this.cccd;
    }

    public Employee cccd(Integer cccd) {
        this.setCccd(cccd);
        return this;
    }

    public void setCccd(Integer cccd) {
        this.cccd = cccd;
    }

    public String getAddress() {
        return this.address;
    }

    public Employee address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBankAccountNumber() {
        return this.bankAccountNumber;
    }

    public Employee bankAccountNumber(Integer bankAccountNumber) {
        this.setBankAccountNumber(bankAccountNumber);
        return this;
    }

    public void setBankAccountNumber(Integer bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBank() {
        return this.bank;
    }

    public Employee bank(String bank) {
        this.setBank(bank);
        return this;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return this.bankBranch;
    }

    public Employee bankBranch(String bankBranch) {
        this.setBankBranch(bankBranch);
        return this;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public byte[] getPhotoPath() {
        return this.photoPath;
    }

    public Employee photoPath(byte[] photoPath) {
        this.setPhotoPath(photoPath);
        return this;
    }

    public void setPhotoPath(byte[] photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPathContentType() {
        return this.photoPathContentType;
    }

    public Employee photoPathContentType(String photoPathContentType) {
        this.photoPathContentType = photoPathContentType;
        return this;
    }

    public void setPhotoPathContentType(String photoPathContentType) {
        this.photoPathContentType = photoPathContentType;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee user(User user) {
        this.setUser(user);
        return this;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee job(Job job) {
        this.setJob(job);
        return this;
    }

    public Set<TimeKeeping> getTimeKeepings() {
        return this.timeKeepings;
    }

    public void setTimeKeepings(Set<TimeKeeping> timeKeepings) {
        if (this.timeKeepings != null) {
            this.timeKeepings.forEach(i -> i.setEmployee(null));
        }
        if (timeKeepings != null) {
            timeKeepings.forEach(i -> i.setEmployee(this));
        }
        this.timeKeepings = timeKeepings;
    }

    public Employee timeKeepings(Set<TimeKeeping> timeKeepings) {
        this.setTimeKeepings(timeKeepings);
        return this;
    }

    public Employee addTimeKeeping(TimeKeeping timeKeeping) {
        this.timeKeepings.add(timeKeeping);
        timeKeeping.setEmployee(this);
        return this;
    }

    public Employee removeTimeKeeping(TimeKeeping timeKeeping) {
        this.timeKeepings.remove(timeKeeping);
        timeKeeping.setEmployee(null);
        return this;
    }

    public Set<LeaveRequest> getLeaveRequests() {
        return this.leaveRequests;
    }

    public void setLeaveRequests(Set<LeaveRequest> leaveRequests) {
        if (this.leaveRequests != null) {
            this.leaveRequests.forEach(i -> i.setEmployee(null));
        }
        if (leaveRequests != null) {
            leaveRequests.forEach(i -> i.setEmployee(this));
        }
        this.leaveRequests = leaveRequests;
    }

    public Employee leaveRequests(Set<LeaveRequest> leaveRequests) {
        this.setLeaveRequests(leaveRequests);
        return this;
    }

    public Employee addLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequests.add(leaveRequest);
        leaveRequest.setEmployee(this);
        return this;
    }

    public Employee removeLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequests.remove(leaveRequest);
        leaveRequest.setEmployee(null);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee department(Department department) {
        this.setDepartment(department);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
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
            ", photoPathContentType='" + getPhotoPathContentType() + "'" +
            "}";
    }
}

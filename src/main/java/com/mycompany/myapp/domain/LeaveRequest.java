package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.LeaveRequestStatus;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A LeaveRequest.
 */
@Entity
@Table(name = "leave_request")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaveRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LeaveRequestStatus status;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "job", "timeKeepings", "leaveRequests", "department" }, allowSetters = true)
    private Employee employee;

    @ManyToOne
    @JsonIgnoreProperties(value = { "leaveRequests" }, allowSetters = true)
    private LeaveType leaveType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeaveRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LeaveRequest startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public LeaveRequest endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveRequestStatus getStatus() {
        return this.status;
    }

    public LeaveRequest status(LeaveRequestStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(LeaveRequestStatus status) {
        this.status = status;
    }

    public String getReason() {
        return this.reason;
    }

    public LeaveRequest reason(String reason) {
        this.setReason(reason);
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveRequest employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    public LeaveType getLeaveType() {
        return this.leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public LeaveRequest leaveType(LeaveType leaveType) {
        this.setLeaveType(leaveType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaveRequest)) {
            return false;
        }
        return id != null && id.equals(((LeaveRequest) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaveRequest{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", reason='" + getReason() + "'" +
            "}";
    }
}

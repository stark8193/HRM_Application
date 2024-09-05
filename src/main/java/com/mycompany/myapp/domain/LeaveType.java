package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A LeaveType.
 */
@Entity
@Table(name = "leave_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaveType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "leave_type_name", nullable = false)
    private String leaveTypeName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "leaveType")
    @JsonIgnoreProperties(value = { "employee", "leaveType" }, allowSetters = true)
    private Set<LeaveRequest> leaveRequests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeaveType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveTypeName() {
        return this.leaveTypeName;
    }

    public LeaveType leaveTypeName(String leaveTypeName) {
        this.setLeaveTypeName(leaveTypeName);
        return this;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public String getDescription() {
        return this.description;
    }

    public LeaveType description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<LeaveRequest> getLeaveRequests() {
        return this.leaveRequests;
    }

    public void setLeaveRequests(Set<LeaveRequest> leaveRequests) {
        if (this.leaveRequests != null) {
            this.leaveRequests.forEach(i -> i.setLeaveType(null));
        }
        if (leaveRequests != null) {
            leaveRequests.forEach(i -> i.setLeaveType(this));
        }
        this.leaveRequests = leaveRequests;
    }

    public LeaveType leaveRequests(Set<LeaveRequest> leaveRequests) {
        this.setLeaveRequests(leaveRequests);
        return this;
    }

    public LeaveType addLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequests.add(leaveRequest);
        leaveRequest.setLeaveType(this);
        return this;
    }

    public LeaveType removeLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequests.remove(leaveRequest);
        leaveRequest.setLeaveType(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaveType)) {
            return false;
        }
        return id != null && id.equals(((LeaveType) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaveType{" +
            "id=" + getId() +
            ", leaveTypeName='" + getLeaveTypeName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

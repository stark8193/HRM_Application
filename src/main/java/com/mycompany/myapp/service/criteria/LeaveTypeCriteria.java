package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.LeaveType} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.LeaveTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /leave-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaveTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter leaveTypeName;

    private StringFilter description;

    private LongFilter leaveRequestId;

    private Boolean distinct;

    public LeaveTypeCriteria() {}

    public LeaveTypeCriteria(LeaveTypeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.leaveTypeName = other.leaveTypeName == null ? null : other.leaveTypeName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.leaveRequestId = other.leaveRequestId == null ? null : other.leaveRequestId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public LeaveTypeCriteria copy() {
        return new LeaveTypeCriteria(this);
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

    public StringFilter getLeaveTypeName() {
        return leaveTypeName;
    }

    public StringFilter leaveTypeName() {
        if (leaveTypeName == null) {
            leaveTypeName = new StringFilter();
        }
        return leaveTypeName;
    }

    public void setLeaveTypeName(StringFilter leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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
        final LeaveTypeCriteria that = (LeaveTypeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(leaveTypeName, that.leaveTypeName) &&
            Objects.equals(description, that.description) &&
            Objects.equals(leaveRequestId, that.leaveRequestId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaveTypeName, description, leaveRequestId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaveTypeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (leaveTypeName != null ? "leaveTypeName=" + leaveTypeName + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (leaveRequestId != null ? "leaveRequestId=" + leaveRequestId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

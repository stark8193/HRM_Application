package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.TimeKeeping} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TimeKeepingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /time-keepings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TimeKeepingCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter date;

    private ZonedDateTimeFilter checkIn;

    private ZonedDateTimeFilter checkOut;

    private LongFilter employeeId;

    private Boolean distinct;

    public TimeKeepingCriteria() {}

    public TimeKeepingCriteria(TimeKeepingCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.checkIn = other.checkIn == null ? null : other.checkIn.copy();
        this.checkOut = other.checkOut == null ? null : other.checkOut.copy();
        this.employeeId = other.employeeId == null ? null : other.employeeId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TimeKeepingCriteria copy() {
        return new TimeKeepingCriteria(this);
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

    public LocalDateFilter getDate() {
        return date;
    }

    public LocalDateFilter date() {
        if (date == null) {
            date = new LocalDateFilter();
        }
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public ZonedDateTimeFilter getCheckIn() {
        return checkIn;
    }

    public ZonedDateTimeFilter checkIn() {
        if (checkIn == null) {
            checkIn = new ZonedDateTimeFilter();
        }
        return checkIn;
    }

    public void setCheckIn(ZonedDateTimeFilter checkIn) {
        this.checkIn = checkIn;
    }

    public ZonedDateTimeFilter getCheckOut() {
        return checkOut;
    }

    public ZonedDateTimeFilter checkOut() {
        if (checkOut == null) {
            checkOut = new ZonedDateTimeFilter();
        }
        return checkOut;
    }

    public void setCheckOut(ZonedDateTimeFilter checkOut) {
        this.checkOut = checkOut;
    }

    public LongFilter getEmployeeId() {
        return employeeId;
    }

    public LongFilter employeeId() {
        if (employeeId == null) {
            employeeId = new LongFilter();
        }
        return employeeId;
    }

    public void setEmployeeId(LongFilter employeeId) {
        this.employeeId = employeeId;
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
        final TimeKeepingCriteria that = (TimeKeepingCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(checkIn, that.checkIn) &&
            Objects.equals(checkOut, that.checkOut) &&
            Objects.equals(employeeId, that.employeeId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, checkIn, checkOut, employeeId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimeKeepingCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (checkIn != null ? "checkIn=" + checkIn + ", " : "") +
            (checkOut != null ? "checkOut=" + checkOut + ", " : "") +
            (employeeId != null ? "employeeId=" + employeeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

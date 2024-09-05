package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TimeKeeping} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TimeKeepingDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private ZonedDateTime checkIn;

    private ZonedDateTime checkOut;

    private EmployeeDTO employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ZonedDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(ZonedDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public ZonedDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(ZonedDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeKeepingDTO)) {
            return false;
        }

        TimeKeepingDTO timeKeepingDTO = (TimeKeepingDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, timeKeepingDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimeKeepingDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", checkIn='" + getCheckIn() + "'" +
            ", checkOut='" + getCheckOut() + "'" +
            ", employee=" + getEmployee() +
            "}";
    }
}

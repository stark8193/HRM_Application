package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TimeKeeping.
 */
@Entity
@Table(name = "time_keeping")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TimeKeeping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "check_in", nullable = false)
    private ZonedDateTime checkIn;

    @Column(name = "check_out")
    private ZonedDateTime checkOut;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "job", "timeKeepings", "leaveRequests", "department" }, allowSetters = true)
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TimeKeeping id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public TimeKeeping date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ZonedDateTime getCheckIn() {
        return this.checkIn;
    }

    public TimeKeeping checkIn(ZonedDateTime checkIn) {
        this.setCheckIn(checkIn);
        return this;
    }

    public void setCheckIn(ZonedDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public ZonedDateTime getCheckOut() {
        return this.checkOut;
    }

    public TimeKeeping checkOut(ZonedDateTime checkOut) {
        this.setCheckOut(checkOut);
        return this;
    }

    public void setCheckOut(ZonedDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TimeKeeping employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeKeeping)) {
            return false;
        }
        return id != null && id.equals(((TimeKeeping) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimeKeeping{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", checkIn='" + getCheckIn() + "'" +
            ", checkOut='" + getCheckOut() + "'" +
            "}";
    }
}

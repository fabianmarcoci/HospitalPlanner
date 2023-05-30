package org.example.HospitalPlanner.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {
    private String specialization;
    private LocalDate startedWorkingAt;
    private int yearsOfExperience;

    public Doctor() {}

    public Doctor(String name, LocalDate birthDate, String gender,
                  String citizenship, String mail, String specialization, LocalDate startedWorkingAt) {

        super(name, birthDate, gender, citizenship, mail);
        this.specialization = specialization;
        this.startedWorkingAt = startedWorkingAt;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getStartedWorkingAt() {
        return startedWorkingAt;
    }

    public void setStartedWorkingAt(LocalDate startedWorkingAt) {
        this.startedWorkingAt = startedWorkingAt;
    }

    public int getYearsOfExperience() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(startedWorkingAt, currentDate);
        yearsOfExperience = period.getYears();
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                ", specialization='" + specialization + '\'' +
                ", yearsOfExperience=" + getYearsOfExperience() +
                '}';
    }
}

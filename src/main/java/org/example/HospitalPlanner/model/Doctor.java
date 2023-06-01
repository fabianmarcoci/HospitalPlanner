package org.example.HospitalPlanner.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "doctor")
public class Doctor extends Person {
    private String specialization;
    @Column(name = "started_working_at")
    private LocalDate startedWorkingAt;
    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    public Doctor() {}

    public Doctor(String name, LocalDate birthDate, String gender,
                  String citizenship, String mail, String role, String specialization, LocalDate startedWorkingAt) {
        super(name, birthDate, gender, citizenship, mail, role);
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

    public void setYearsOfExperience() {
        this.yearsOfExperience = getYearsOfExperience();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                ", specialization='" + specialization + '\'' +
                ", startedWorkingAt=" + startedWorkingAt +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}

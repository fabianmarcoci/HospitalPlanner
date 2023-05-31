package org.example.HospitalPlanner.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String specialization;
    private LocalDate startedWorkingAt;
    private int yearsOfExperience;
    private String email;

    // Assuming age is derived from birthDate
    @Transient
    private int age;

    public Doctor() {}

    public Doctor(String name, LocalDate birthDate, String gender,
                  String citizenship, String mail, String specialization, LocalDate startedWorkingAt) {
        super(name, birthDate, gender, citizenship, mail);
        this.specialization = specialization;
        this.startedWorkingAt = startedWorkingAt;
        this.email = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(getBirthDate(), currentDate);
        age = period.getYears();
        return age;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                ", startedWorkingAt=" + startedWorkingAt +
                ", yearsOfExperience=" + yearsOfExperience +
                ", email='" + email + '\'' +
                ", age=" + getAge() +
                '}';
    }
}

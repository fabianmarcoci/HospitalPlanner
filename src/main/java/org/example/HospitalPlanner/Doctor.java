package org.example.HospitalPlanner;
import java.time.LocalDate;
import java.time.Period;
public class Doctor extends Person {
    private String specialization;
    private LocalDate startedWorkingAt;
    private int yearsOfExperience;

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


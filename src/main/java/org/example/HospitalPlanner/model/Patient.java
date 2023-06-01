package org.example.HospitalPlanner.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name = "patient")
public class Patient extends Person {
    private String disease;

    public Patient() {

    }

    public Patient(String name, LocalDate birthDate, String gender, String citizenship,
                   String mail, String role, String disease) {
        super(name, birthDate, gender, citizenship, mail, role);
        this.disease = disease;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                ", disease='" + disease + '\'' +
                '}';
    }
}

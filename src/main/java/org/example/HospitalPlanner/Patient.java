package org.example.HospitalPlanner;
import java.time.LocalDate;
public class Patient extends Person{
    private String disease;

    public Patient(String name, LocalDate birthDate, String gender, String citizenship, String mail, String disease) {
        super(name, birthDate, gender, citizenship, mail);
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

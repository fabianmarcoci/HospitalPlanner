package org.example;
import java.time.LocalDate;
public class Patient extends Person{
    private String disease;

    public Patient(String name, LocalDate birthDate, String sex, String citizenship, String disease) {
        super(name, birthDate, sex, citizenship);
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

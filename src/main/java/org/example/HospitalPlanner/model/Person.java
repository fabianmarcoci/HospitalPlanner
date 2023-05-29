package org.example.HospitalPlanner.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "persons")
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private LocalDate birthDate;
    private String gender;
    private String citizenship;
    private String mail;
    public Person(String name, LocalDate birthDate, String gender, String citizenship, String mail) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.citizenship = citizenship;
        this.mail = mail;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        age = period.getYears();
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + getAge() +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", mail='" + mail + '\'';
    }
}

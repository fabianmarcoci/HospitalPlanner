package org.example;
import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
    private int id;
    private String name;
    private int age;
    private LocalDate birthDate;
    private String sex;
    private String citizenship;
    public Person(String name, LocalDate birthDate, String sex, String citizenship) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.citizenship = citizenship;
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

    public void setId(int id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + getAge() +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", citizenship='" + citizenship + '\'';
    }
}

package org.example.HospitalPlanner.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    private String day;
    @Column(name = "time_slot")
    private String timeSlot;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Transient
    private String[] times = new String[9]; // 9 slots from 9:00 to 17:00

    public Schedule(String day, String[] times) {
        this.day = day;
        for (int i = 0; i < this.times.length; i++) {
            this.times[i] = times[i];
        }
    }

    public Schedule() {
    }

    public int getTimeSlotIndex(String timeSlot) {
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime inputTime = LocalTime.parse(timeSlot);

        long hoursBetween = ChronoUnit.HOURS.between(startTime, inputTime);

        return (int) hoursBetween;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getDay() {
        return day;
    }

    public String dayProperty() {
        return day;
    }

    public String getTime0() {
        return times[0];
    }

    public String getTime1() {
        return times[1];
    }

    public String getTime2() {
        return times[2];
    }

    public String getTime3() {
        return times[3];
    }

    public String getTime4() {
        return times[4];
    }

    public String getTime5() {
        return times[5];
    }

    public String getTime6() {
        return times[6];
    }

    public String getTime7() {
        return times[7];
    }

    public String getTime8() {
        return times[8];
    }
}


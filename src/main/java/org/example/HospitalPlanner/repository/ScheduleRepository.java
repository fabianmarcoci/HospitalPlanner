package org.example.HospitalPlanner.repository;

import org.example.HospitalPlanner.model.Doctor;
import org.example.HospitalPlanner.model.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    Optional<List<Schedule>> findByDoctor(Doctor doctor);
    Optional<Schedule> findByDoctorAndDay(Doctor doctor, String day);

    Optional<Schedule> findByDoctorAndDayAndTimeSlot(Doctor doctor, String day, String timeSlot);
}

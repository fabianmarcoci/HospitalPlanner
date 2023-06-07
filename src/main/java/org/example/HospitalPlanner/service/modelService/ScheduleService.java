package org.example.HospitalPlanner.service.modelService;

import org.example.HospitalPlanner.model.Doctor;
import org.example.HospitalPlanner.model.Schedule;
import org.example.HospitalPlanner.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createAppointment(Schedule schedule) {
        // Find if there's any existing appointment at the same time.
        Optional<Schedule> existingAppointment = scheduleRepository.findByDoctorAndDayAndTimeSlot(
                schedule.getDoctor(),
                schedule.getDay(),
                schedule.getTimeSlot()
        );

        if (existingAppointment.isPresent()) {
            logger.error("Appointment already exists at the specified time");
            return null;
        }

        try {
            scheduleRepository.save(schedule);
            logger.info("Appointment created successfully");
            return schedule;
        } catch (DataAccessException e) {
            logger.error("Failed to create appointment", e);
            return null;
        }
    }


    public List<Schedule> getAllAppointments() {
        try {
            return (List<Schedule>) scheduleRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve appointments", e);
            throw e;
        }
    }

    public Optional<List<Schedule>> getAppointmentsByDoctor(Doctor doctor) {
        try {
            return scheduleRepository.findByDoctor(doctor);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve appointments where doctor=" + doctor.getId());
            throw e;
        }
    }

    public Optional<Schedule> getScheduleById(Integer id) {
        try {
            return scheduleRepository.findById(id);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve schedule with id " + id, e);
            throw e;
        }
    }

    public Optional<Schedule> getScheduleByDoctorAndDay(Doctor doctor, String day) {
        try {
            return scheduleRepository.findByDoctorAndDay(doctor, day);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve schedule with doctor " + doctor.getId() + " and day " + day );
            throw e;
        }
    }

    public List<Schedule> getSchedulesByDoctorId(int doctorId) {
        try {
            return scheduleRepository.findByDoctorId(doctorId);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve schedules for doctor " + doctorId);
            throw e;
        }
    }

    public boolean updateSchedule(Schedule schedule) {
        try {
            if(scheduleRepository.existsById(schedule.getId())) {
                scheduleRepository.save(schedule);
                logger.info("Schedule updated successfully");
                return true;
            } else {
                logger.error("Schedule with id " + schedule.getId() + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to update schedule", e);
            return false;
        }
    }

    public boolean deleteSchedule(Integer id) {
        try {
            if(scheduleRepository.existsById(id)) {
                scheduleRepository.deleteById(id);
                logger.info("Schedule deleted successfully");
                return true;
            } else {
                logger.error("Schedule with id " + id + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to delete schedule", e);
            return false;
        }
    }
}


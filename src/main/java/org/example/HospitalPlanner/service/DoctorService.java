package org.example.HospitalPlanner.service;

import org.example.HospitalPlanner.model.Doctor;
import org.example.HospitalPlanner.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public boolean createDoctor(Doctor doctor) {
        try {
            doctorRepository.save(doctor);
            logger.info("Doctor created successfully");
            return true;
        } catch (DataAccessException e) {
            logger.error("Failed to create doctor", e);
            return false;
        }
    }

    public List<Doctor> getAllDoctors() {
        try {
            return (List<Doctor>) doctorRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve doctors", e);
            throw e;
        }
    }

    public Optional<Doctor> getDoctorById(Integer id) {
        try {
            return doctorRepository.findById(id);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve doctor with id " + id, e);
            throw e;
        }
    }

    public boolean updateDoctor(Doctor doctor) {
        try {
            if(doctorRepository.existsById(doctor.getId())) {
                doctorRepository.save(doctor);
                logger.info("Doctor updated successfully");
                return true;
            } else {
                logger.error("Doctor with id " + doctor.getId() + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to update doctor", e);
            return false;
        }
    }

    public boolean deleteDoctor(Integer id) {
        try {
            if(doctorRepository.existsById(id)) {
                doctorRepository.deleteById(id);
                logger.info("Doctor deleted successfully");
                return true;
            } else {
                logger.error("Doctor with id " + id + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to delete doctor", e);
            return false;
        }
    }
}

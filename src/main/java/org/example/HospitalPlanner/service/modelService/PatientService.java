package org.example.HospitalPlanner.service.modelService;

import org.example.HospitalPlanner.model.Patient;
import org.example.HospitalPlanner.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        try {
            patientRepository.save(patient);
            logger.info("Patient created successfully");
            return patient;
        } catch (DataAccessException e) {
            logger.error("Failed to create patient", e);
            return null;
        }
    }

    public List<Patient> getAllPatients() {
        try {
            return (List<Patient>) patientRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve patients", e);
            throw e;
        }
    }

    public Optional<Patient> getPatientById(Integer id) {
        try {
            return patientRepository.findById(id);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve patient with id " + id, e);
            throw e;
        }
    }

    public boolean updatePatient(Patient patient) {
        try {
            if(patientRepository.existsById(patient.getId())) {
                patientRepository.save(patient);
                logger.info("Patient updated successfully");
                return true;
            } else {
                logger.error("Patient with id " + patient.getId() + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to update patient", e);
            return false;
        }
    }

    public boolean deletePatient(Integer id) {
        try {
            if(patientRepository.existsById(id)) {
                patientRepository.deleteById(id);
                logger.info("Patient deleted successfully");
                return true;
            } else {
                logger.error("Patient with id " + id + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to delete patient", e);
            return false;
        }
    }
}

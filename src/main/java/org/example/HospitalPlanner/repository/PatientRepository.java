package org.example.HospitalPlanner.repository;

import org.example.HospitalPlanner.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}

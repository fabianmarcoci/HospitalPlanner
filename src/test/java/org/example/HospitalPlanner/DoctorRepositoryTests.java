package org.example.HospitalPlanner;

import org.example.HospitalPlanner.repository.DoctorRepository;
import org.example.HospitalPlanner.model.Doctor;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

@DataJpaTest
@Rollback(false)
public class DoctorRepositoryTests {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testAddNew() {
        Doctor doctor = new Doctor();
        doctor.setMail("vladalexandrugheras@yahoo.com");
        doctor.setGender("male");
        doctor.setName("Gheras Vlad");

          doctorRepository.save(doctor);


        Doctor found = doctorRepository.findById(doctor.getId()).orElse(null);
        assertThat(found).isEqualTo(doctor);
    }


}

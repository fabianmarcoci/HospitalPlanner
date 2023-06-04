package org.example.HospitalPlanner.manager;

import org.example.HospitalPlanner.ui.form.DoctorForm;
import org.example.HospitalPlanner.ui.form.PatientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FXWindowManager {

    @Autowired
    private DoctorForm DoctorForm;

    @Autowired
    private PatientForm PatientForm;

    public void openDoctor(String username) {
        DoctorForm.createForm();
    }

    public void openPatient(String username) {
        PatientForm.createForm();
    }
}

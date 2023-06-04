package org.example.HospitalPlanner.manager;

import org.example.HospitalPlanner.config.ApplicationContextProvider;
import org.example.HospitalPlanner.service.AccountCheck;
import org.example.HospitalPlanner.ui.form.DoctorForm;
import org.example.HospitalPlanner.ui.form.PatientForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FXWindowManager {

    @Autowired
    private DoctorForm doctorForm;

    @Autowired
    private PatientForm patientForm;

    public void openDoctor(String username) {
        AccountCheck accountCheck = ApplicationContextProvider.getApplicationContext().getBean(AccountCheck.class);
        String name = accountCheck.retrieveName(username);
        doctorForm.foundName(name);
        doctorForm.createForm();
    }

    public void openPatient(String username) {
        AccountCheck accountCheck = ApplicationContextProvider.getApplicationContext().getBean(AccountCheck.class);
        String name = accountCheck.retrieveName(username);
        patientForm.foundName(name);
        patientForm.createForm();
    }
}

package org.example.HospitalPlanner.ui.form;

import org.springframework.stereotype.Component;

@Component
public class PatientForm extends Form {

    @Override
    public String getFxmlPath() {
        return "/fxml/patientForm.fxml";
    }

    @Override
    public String getTitle() {
        return "Patient form";
    }
}

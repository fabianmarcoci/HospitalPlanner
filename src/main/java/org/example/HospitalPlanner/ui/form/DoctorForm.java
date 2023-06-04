package org.example.HospitalPlanner.ui.form;

import org.springframework.stereotype.Component;

@Component
public class DoctorForm extends Form {

    @Override
    public String getFxmlPath() {
        return "/fxml/doctorForm.fxml";
    }

    @Override
    public String getTitle() {
        return "Doctor schedule";
    }
}

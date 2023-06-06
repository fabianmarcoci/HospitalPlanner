package org.example.HospitalPlanner.ui.form;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class DoctorForm extends Form {

    @FXML
    private AnchorPane doctorForm;
    @FXML
    private Label scheduleLabel;

    @Override
    public String getFxmlPath() {
        return "/fxml/doctorForm.fxml";
    }

    @Override
    public String getTitle() {
        return "Doctor schedule";
    }


}

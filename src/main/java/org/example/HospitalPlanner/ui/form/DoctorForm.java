package org.example.HospitalPlanner.ui.form;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DoctorForm {
    @Autowired
    private ConfigurableApplicationContext context;
    public void createForm() {
        init();
    }

    public void init() {
        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(context::getBean);
                fxmlLoader.setLocation(getClass().getResource("/fxml/doctorForm.fxml"));

                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Doctor schedule");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

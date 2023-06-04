package org.example.HospitalPlanner.ui.form;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public abstract class Form {
    @Autowired
    protected ConfigurableApplicationContext context;
    protected String name;

    public void createForm() {
        init();
    }

    public abstract String getFxmlPath();

    public abstract String getTitle();

    public void init() {
        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(context::getBean);
                fxmlLoader.setLocation(getClass().getResource(getFxmlPath()));

                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle(getTitle());
                stage.setScene(new Scene(root, 800, 600));

                stage.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(0);
                });

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void foundName(String name) {
        this.name = name;
    }
}

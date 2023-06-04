package org.example.HospitalPlanner.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.HospitalPlanner.manager.FXWindowManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JavaFXApp extends Application {
    private static JavaFXApp instance;
    private static ConfigurableApplicationContext springContext;
    private FXWindowManager windowManager;

    public static JavaFXApp getInstance() {
        return instance;
    }

    public static void setSpringContext(ConfigurableApplicationContext context) {
        JavaFXApp.springContext = context;
    }

    public JavaFXApp() {
        instance = this;
    }

    @Override
    public void init() throws Exception {
        // Initialize WindowManager with Spring Context
        this.windowManager = springContext.getBean(FXWindowManager.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    public FXWindowManager getWindowManager() {
        return windowManager;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (springContext != null) {
            springContext.close();
        }
    }
}

package org.example.HospitalPlanner;

import org.example.HospitalPlanner.manager.HospitalManager;
import org.example.HospitalPlanner.ui.JavaFXApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static javafx.application.Application.launch;


@SpringBootApplication
@EnableJpaRepositories("org.example.HospitalPlanner.repository")
@EntityScan("org.example.HospitalPlanner.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        // Start Spring application
        ConfigurableApplicationContext context = application.run(args);

        // Pass spring context to JavaFX
        JavaFXApp.setSpringContext(context);

        // Start Swing app
        HospitalManager manager = context.getBean(HospitalManager.class);
        manager.run();

        // Start JavaFX
        launch(JavaFXApp.class, args);
    }

}
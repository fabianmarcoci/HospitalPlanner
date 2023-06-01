package org.example.HospitalPlanner;

import org.example.HospitalPlanner.manager.HospitalManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("org.example.HospitalPlanner.repository")
@EntityScan("org.example.HospitalPlanner.model")
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        HospitalManager manager = context.getBean(HospitalManager.class);
        manager.run();
    }
}
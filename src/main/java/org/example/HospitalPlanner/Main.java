package org.example.HospitalPlanner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });

        new Thread(() -> {
            Server server = new Server();
            server.startServer();
        }).start();

        LocalDate fabianBirthDate = LocalDate.of(2002, 8, 25); // August 25th, 2002
        Person fabian = new Patient("Fabian", fabianBirthDate, "masculine",
                "romanian", "marcocifabian16@gmail.com", "lung problems");
        System.out.println(fabian);

        LocalDate vladBirthDate = LocalDate.of(2003, 1, 10); // January 10th, 1985
        LocalDate vladDebut = LocalDate.of(2010, 11, 15);
        Person vlad = new Doctor("Vlad", vladBirthDate, "masculine", "romanian",
                "vladalexandrugheras@yahoo.com", "lung", vladDebut);
        System.out.println(vlad);
    }
}
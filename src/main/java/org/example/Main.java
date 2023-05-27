package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            Server server = new Server();
            server.startServer();
        }).start();

        new MainFrame().setVisible(true);

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
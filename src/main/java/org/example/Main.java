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
        new MainFrame().setVisible(true);

        LocalDate eugenBirthDate = LocalDate.of(2000, 5, 24); // May 24th, 2000
        Person eugen = new Patient("Eugen", eugenBirthDate, "masculine",
                "romanian", "lung problems");
        System.out.println(eugen);

        LocalDate mihailBirthDate = LocalDate.of(1984, 10, 21); // October 21st, 1984
        LocalDate mihailDebut = LocalDate.of(2010, 11, 15);
        Person mihail = new Doctor("Mihail", mihailBirthDate, "masculine", "romanian", "lung", mihailDebut);
        System.out.println(mihail);
    }
}
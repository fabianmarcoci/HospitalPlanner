package org.example.HospitalPlanner.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AccountManager {
    private final Logger logger = LoggerFactory.getLogger(AccountManager.class);

    private final Connection connection;

    @Autowired
    public AccountManager(Connection connection) {
        this.connection = connection;
    }

    public String submitLogin(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String storedHashedPassword = resultSet.getString("password_hash");

                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    logger.info("Login successful for user: " + username);
                    // Return the user's role
                    return resultSet.getString("role");
                } else {
                    logger.info("Invalid password for user: " + username);
                }
            } else {
                logger.info("Invalid username: " + username);
            }

        } catch (SQLException e) {
            logger.error("Failed to execute query", e);
        }

        return null;
    }
}

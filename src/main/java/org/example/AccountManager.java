package org.example;

import org.example.Database;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AccountManager {

    public static boolean submitLogin(String username, String password) {
        final Logger logger = LoggerFactory.getLogger(AccountManager.class);
        Connection connection = Database.getConnection();
        try {
            // Using a PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Retrieve the hashed password from the database
                String storedHashedPassword = resultSet.getString("password_hash");

                // Compare the entered password with the stored hashed password
                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    logger.info("Login successful for user: " + username);
                    return true;
                } else {
                    logger.info("Invalid password for user: " + username);
                    return false;
                }
            } else {
                logger.info("Invalid username: " + username);
                return false;
            }

        } catch (SQLException e) {
            logger.error("Failed to execute query", e);
        } finally {
            Database.closeConnection(connection);
        }

        return false;
    }
}
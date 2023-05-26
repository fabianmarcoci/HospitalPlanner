package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AccountManager {

    public static boolean submitLogin(String username, String password) {
        final Logger logger = LoggerFactory.getLogger(AccountManager.class);
        Connection connection = Database.getConnection();
        try {
            // Using a PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                logger.info("Login successful for user: " + username);
                return true;
            } else {
                logger.info("Invalid username/password for user: " + username);
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
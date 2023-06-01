package org.example.HospitalPlanner.service;

import org.example.HospitalPlanner.model.Account;
import org.example.HospitalPlanner.service.modelService.AccountService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCheck {
    private final Logger logger = LoggerFactory.getLogger(AccountCheck.class);

    private final AccountService accountService;

    @Autowired
    public AccountCheck(AccountService accountService) {
        this.accountService = accountService;
    }

    public String submitLogin(String username, String password) {
        Optional<Account> accountOpt = accountService.getAccountByUsername(username);

        if(accountOpt.isPresent()){
            Account account = accountOpt.get();
            String storedHashedPassword = account.getPassword();

            if (BCrypt.checkpw(password, storedHashedPassword)) {
                logger.info("Login successful for user: " + username);
                // Return the user's role
                return account.getPerson().getRole();
            } else {
                logger.info("Invalid password for user: " + username);
            }
        } else {
            logger.info("Invalid username: " + username);
        }

        return null;
    }
}


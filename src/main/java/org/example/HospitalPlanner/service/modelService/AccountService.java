package org.example.HospitalPlanner.service.modelService;

import org.example.HospitalPlanner.model.Account;
import org.example.HospitalPlanner.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean createAccount(Account account) {
        try {
            accountRepository.save(account);
            logger.info("Account created successfully");
            return true;
        } catch (DataAccessException e) {
            logger.error("Failed to create account", e);
            return false;
        }
    }

    public List<Account> getAllAccounts() {
        try {
            return (List<Account>) accountRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve accounts", e);
            throw e;
        }
    }


    public Optional<Account> getAccountByUsername(String username) {
        try {
            return accountRepository.findByUsername(username);
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve account with username " + username, e);
            throw e;
        }
    }

    public boolean updateAccount(Account account) {
        try {
            if(accountRepository.existsByUsername(account.getUsername())) {
                accountRepository.save(account);
                logger.info("Account updated successfully");
                return true;
            } else {
                logger.error("Account with username " + account.getUsername() + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to update account", e);
            return false;
        }
    }

    public boolean deleteAccount(Integer id) {
        try {
            if(accountRepository.existsById(id)) {
                accountRepository.deleteById(id);
                logger.info("Account deleted successfully");
                return true;
            } else {
                logger.error("Account with id " + id + " does not exist");
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("Failed to delete account", e);
            return false;
        }
    }
}

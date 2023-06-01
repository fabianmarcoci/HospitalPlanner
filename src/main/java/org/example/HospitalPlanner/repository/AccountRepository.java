package org.example.HospitalPlanner.repository;

import org.example.HospitalPlanner.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);
}

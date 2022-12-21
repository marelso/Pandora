package com.pandora.backend.repository;

import com.pandora.backend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByDeletedFalse();

    Account findByUsername(String username);
}

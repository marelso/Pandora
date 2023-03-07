package com.pandora.backend.repository;

import com.pandora.backend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByDeletedFalse();

    Optional<Account> findByUsername(String username);
}

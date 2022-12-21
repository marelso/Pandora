package com.pandora.backend.repository;

import com.pandora.backend.domain.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {
    List<Accounts> findAllByDeletedFalse();

    Accounts findByUsername(String username);
}

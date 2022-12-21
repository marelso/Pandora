package com.pandora.backend.service;


import com.pandora.backend.domain.Accounts;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountsService {
    @Autowired
    private AccountsRepository repository;

    public List<Accounts> findAll() {
        return this.repository.findAllByDeletedFalse();
    }

    public Accounts findById(Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no account with id: ${id}"));
    }

    public Accounts save(Accounts account) {
        return this.repository.save(account);
    }

    public void delete(Integer id) {
        this.repository.deleteById(id);
    }
}

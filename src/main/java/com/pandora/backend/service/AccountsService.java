package com.pandora.backend.service;


import com.pandora.backend.domain.Account;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountsService {
    @Autowired
    private AccountsRepository repository;

    public List<Account> findAll() {
        return this.repository.findAllByDeletedFalse();
    }

    public Account findById(Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no account with id: ${id}"));
    }

    public Account save(Account account) {
        return this.repository.save(account);
    }

    public void delete(Integer id) {
        this.repository.deleteById(id);
    }
}

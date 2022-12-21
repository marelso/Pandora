package com.pandora.backend.controller;

import com.pandora.backend.domain.Account;
import com.pandora.backend.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("accounts")
public class AccountsController {
    @Autowired
    private AccountsService service;

    @GetMapping
    public List<Account> getAll() {
        return this.service.findAll();
    }

    @GetMapping("/id")
    public Account get(@PathVariable Integer id) {
        return this.service.findById(id);
    }

    @PostMapping
    public Account post(@RequestBody Account account) {
        return this.service.save(account);
    }

    @PutMapping
    public Account put(@PathVariable Integer id,
                       @RequestBody Account account) {
        return this.service.save(account);
    }

    @DeleteMapping("/permanent")
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }
}

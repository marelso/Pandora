package com.pandora.backend.controller;

import com.pandora.backend.domain.Account;
import com.pandora.backend.domain.PermissionType;
import com.pandora.backend.service.AccountsService;
import com.pandora.backend.service.auth.AuthService;
import com.pandora.backend.service.auth.JwtService;
import com.pandora.backend.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("accounts")
public class AccountsController {
    @Autowired
    private AccountsService service;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Account> getAll(@RequestHeader("Authorization") String bearerToken) {
        authService.authorize(bearerToken, PermissionType.MANAGER);
        return this.service.findAll();
    }

    @GetMapping("/id")
    public Account get(@RequestHeader("Authorization") String bearerToken,
                       @PathVariable Integer id) {

        authService.authorize(bearerToken, PermissionType.MANAGER);
        return this.service.findById(id);
    }

    @PostMapping
    public Account post(@RequestHeader("Authorization") String bearerToken,
                        @RequestBody Account account) throws NoSuchAlgorithmException {

        authService.authorize(bearerToken, PermissionType.MANAGER);
        account.setPassword(EncryptionService.encrypt(account.getPassword()));

        return this.service.save(account);
    }

    @PutMapping
    public Account put(@RequestHeader("Authorization") String bearerToken,
                       @PathVariable Integer id,
                       @RequestBody Account account) {

        authService.authorize(bearerToken, PermissionType.MANAGER);
        return this.service.save(account);
    }

    @DeleteMapping("/permanent")
    public void delete(@RequestHeader("Authorization") String bearerToken,
                       @PathVariable Integer id) {

        authService.authorize(bearerToken, PermissionType.SUPER);
        this.service.delete(id);
    }
}

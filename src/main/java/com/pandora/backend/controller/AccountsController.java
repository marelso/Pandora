package com.pandora.backend.controller;

import com.pandora.backend.domain.Account;
import com.pandora.backend.domain.Email;
import com.pandora.backend.domain.PermissionType;
import com.pandora.backend.domain.reset.ResetPasswordRequest;
import com.pandora.backend.dto.AccountDto;
import com.pandora.backend.dto.factory.AccountFactory;
import com.pandora.backend.service.AccountsService;
import com.pandora.backend.service.auth.AuthService;
import com.pandora.backend.service.util.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountsController {
    private final AccountsService service;
    private final AuthService authService;
    private final AccountFactory factory;

    @GetMapping
    public List<AccountDto> getAll(@RequestHeader("Authorization") String bearerToken) {
        authService.authorize(bearerToken, PermissionType.MANAGER);
        return factory.from(service.findAll());
    }

    @GetMapping("/{id}")
    public AccountDto get(@RequestHeader("Authorization") String bearerToken,
                       @PathVariable Integer id) {

        authService.authorize(bearerToken, PermissionType.MANAGER);
        return factory.from(service.findById(id));
    }

    @PostMapping
    public AccountDto post(@RequestHeader("Authorization") String bearerToken,
                        @RequestBody Account account) throws NoSuchAlgorithmException {

        authService.authorize(bearerToken, PermissionType.MANAGER);
        account.setPassword(EncryptionService.encrypt(account.getPassword()));

        return factory.from(service.save(account));
    }

    @PostMapping("/reset/{id}")
    public Email resertPassword(@RequestHeader("Authorization") String bearerToken,
                                @PathVariable Integer id,
                                @RequestBody ResetPasswordRequest request) throws NoSuchAlgorithmException {

        authService.authorize(bearerToken, PermissionType.NONE);

        return service.reset(id, request);
    }

    @PutMapping("/{id}")
    public AccountDto put(@RequestHeader("Authorization") String bearerToken,
                       @PathVariable Integer id,
                       @RequestBody Account account) throws NoSuchAlgorithmException {

        authService.authorize(bearerToken, PermissionType.MANAGER);

        if(account.getPassword() != null && !account.getPassword().isEmpty())
            account.setPassword(EncryptionService.encrypt(account.getPassword()));

        return factory.from(service.save(account));
    }

    @DeleteMapping
    public void delete(@RequestHeader("Authorization") String bearerToken,
                       @PathVariable Integer id) {

        authService.authorize(bearerToken, PermissionType.SUPER);
        this.service.delete(id);
    }
}

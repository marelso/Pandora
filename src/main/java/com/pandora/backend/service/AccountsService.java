package com.pandora.backend.service;


import com.pandora.backend.domain.Account;
import com.pandora.backend.domain.Email;
import com.pandora.backend.domain.reset.ResetPasswordRequest;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.AccountsRepository;
import com.pandora.backend.service.util.EncryptionService;
import com.pandora.backend.service.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;


@Service
public class AccountsService implements UserDetailsService {
    @Autowired
    private AccountsRepository repository;
    @Autowired
    private MailService mailService;

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

    public Email reset(Integer id, ResetPasswordRequest request) throws NotFoundException, NoSuchAlgorithmException {
        Account account = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no account with id: ${id}"));

        compare(account, request);

        String pin = String.format("%04d", new Random().nextInt(10_000));
        String body = String.format("Olá, %1$s,\n%2$s é sua nova senha temporária. Ao efetuar o login, por favor, cadastre uma nova senha."
                ,account.getName()
                ,pin);

        account.setPassword(EncryptionService.encrypt(pin));
        account.setHasToSetPassword(true);
        account = this.repository.save(account);

        var email = new Email(null, account.getEmail(), "Pandora - Account recovery", body);

        return mailService.sendEmail(email);
    }

    private boolean compare(Account base, ResetPasswordRequest subject) throws NotFoundException {
        if(subject.getName().equals(base.getName())
                && subject.getLastName().equals(base.getLastName())
                && subject.getUsername().equals(base.getUsername())) {
            return true;
        }

        throw new NotFoundException("Incorrect credentials for account with id: ${id}");
    }

    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username);
    }
}

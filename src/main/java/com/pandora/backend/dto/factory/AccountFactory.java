package com.pandora.backend.dto.factory;

import com.pandora.backend.domain.Account;
import com.pandora.backend.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountFactory {
    public Account from(AccountDto dto) {
        var account = new Account();

        if(dto.getId() != null) {
            account.setId(dto.getId());
        }
        account.setUsername(dto.getName());
        account.setEmail(dto.getEmail());
        account.setName(dto.getName());
        account.setLastName(dto.getLastName());
        account.setDeleted(dto.isDeleted());
        account.setHasToSetPassword(dto.isHasToSetPassword());
        account.setPermissionType(dto.getPermissionType());

        return account;
    }

    public AccountDto from(Account account) {
        var dto = new AccountDto();

        if(account.getId() != null) {
            dto.setId(account.getId());
        }

        dto.setUsername(account.getUsername());
        dto.setEmail(account.getEmail());
        dto.setName(account.getName());
        dto.setLastName(account.getLastName());
        dto.setDeleted(account.isDeleted());
        dto.setHasToSetPassword(account.isHasToSetPassword());
        dto.setPermissionType(account.getPermissionType());

        return dto;
    }

    public List<AccountDto> from(List<Account> accounts) {
        return accounts.stream().map(this::from)
                .collect(Collectors.toList());
    }

    public Account update(Account account, AccountDto dto) {
        account.setUsername(dto.getUsername());
        account.setEmail(dto.getEmail());
        account.setName(dto.getName());
        account.setLastName(dto.getLastName());
        account.setDeleted(dto.isDeleted());
        account.setHasToSetPassword(dto.isHasToSetPassword());
        account.setPermissionType(dto.getPermissionType());

        return account;
    }
}

package com.pandora.backend.service.auth;

import com.pandora.backend.domain.Account;
import com.pandora.backend.domain.PermissionType;
import com.pandora.backend.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private JwtService jwtService;

    public void authorize(String token, PermissionType permission) {
        if(!getUser(token).getPermissionType().hasPermission(permission)) {
            throw new RuntimeException("Feature not allowed for this user.");
        }
    }

    private Account getUser(String token) {
        String username = jwtService.extractUserName(tokenHandler(token));

        return accountsService.loadUserByUsername(username);
    }

    private String tokenHandler(String token) {
        return token.substring(7);
    }
}

package com.pandora.backend.controller;

import com.pandora.backend.domain.auth.AuthRequest;
import com.pandora.backend.domain.auth.AuthResponse;
import com.pandora.backend.service.AccountsService;
import com.pandora.backend.service.auth.JwtService;
import com.pandora.backend.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthRequest authRequest)
            throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername()
                        , EncryptionService.encrypt(authRequest.getPassword())));

        var account = accountsService.loadUserByUsername(authRequest.getUsername());
        var token = jwtService.generateToken(account);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}

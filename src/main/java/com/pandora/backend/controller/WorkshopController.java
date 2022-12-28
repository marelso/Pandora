package com.pandora.backend.controller;

import com.pandora.backend.domain.PermissionType;
import com.pandora.backend.domain.Workshop;
import com.pandora.backend.service.WorkshopService;
import com.pandora.backend.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workshop")
public class WorkshopController {
    @Autowired
    private WorkshopService service;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Workshop> get(@RequestHeader("Authorization") String bearerToken) {
        authService.authorize(bearerToken, PermissionType.MANAGER);

        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public Workshop get(@RequestHeader("Authorization") String bearerToken
            , @PathVariable Integer id) {
        authService.authorize(bearerToken, PermissionType.MANAGER);

        return this.service.findById(id);
    }

    @PostMapping
    public Workshop post(@RequestHeader("Authorization") String bearerToken
            , @RequestBody Workshop workshop) {
        authService.authorize(bearerToken, PermissionType.MANAGER);

        return this.service.save(workshop);
    }

    @PutMapping("/{id}")
    public Workshop put(@RequestHeader("Authorization") String bearerToken
            , @PathVariable Integer id
            , @RequestBody Workshop workshop) {
        authService.authorize(bearerToken, PermissionType.MANAGER);

        return this.service.save(workshop);
    }

    @DeleteMapping
    public void delete(@RequestHeader("Authorization") String bearerToken
            , @PathVariable Integer id) {
        authService.authorize(bearerToken, PermissionType.MANAGER);

        this.service.delete(id);
    }
}

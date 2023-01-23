package com.pandora.backend.service;

import com.pandora.backend.domain.Workshop;
import com.pandora.backend.dto.factory.AccountFactory;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopService {
    private WorkshopRepository repository;
    private AccountsService accountsService;
    private AccountFactory accountFactory;

    public List<Workshop> findAll() {
        return this.repository.findAllByDeletedFalse();
    }

    public Workshop findById(Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no workshop with id: ${id}"));
    }

    public Workshop save(Workshop workshop) {
        workshop.setIncludedBy(accountsService.findByUsername(workshop.getIncludedBy().getUsername()));

        return this.repository.save(workshop);
    }

    public void delete(Integer id) {
        this.repository.deleteById(id);
    }
}

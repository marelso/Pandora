package com.pandora.backend.repository;

import com.pandora.backend.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
    List<Workshop> findAllByDeletedFalse();
}

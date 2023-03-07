package com.pandora.backend.repository;

import com.pandora.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Modifying
    @Query(value = "UPDATE users SET deleted_at = now() WHERE users.id = ?1 ", nativeQuery = true)
    void softDelete(Integer userId);

    List<Category> findAllByDeletedFalse();
}

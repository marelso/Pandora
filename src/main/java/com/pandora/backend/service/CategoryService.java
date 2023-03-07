package com.pandora.backend.service;

import com.pandora.backend.domain.Category;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> findAll() {
        return this.repository.findAllByDeletedFalse();
    }

    public Category findById(Integer id) {
        return get(id);
    }

    private Category get(Integer id) {
        return find(id).orElseThrow(() -> new NotFoundException("User", id));
    }
    private Optional<Category> find(Integer id) {
        return this.repository.findById(id);
    }

    public Category save(Category category) {
        return this.repository.save(category);
    }

    @Transactional
    void delete(Integer id) {
        this.repository.softDelete(id);
    }
}

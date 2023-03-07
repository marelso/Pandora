package com.pandora.backend.service;

import com.pandora.backend.domain.Category;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id));
    }
}

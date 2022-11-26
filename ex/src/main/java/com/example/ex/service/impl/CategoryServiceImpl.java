package com.example.ex.service.impl;

import com.example.ex.model.entity.Category;
import com.example.ex.model.repository.CategoryRepository;
import com.example.ex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;

    public List<Category> findAll() {
        return repo.findAll();
    }

}
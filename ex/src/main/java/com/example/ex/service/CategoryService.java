package com.example.ex.service;

import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Publisher;

import java.util.List;

public interface CategoryService {
   List<Category> findAll();

   Category saveCategory(Category category);
   void deleteCategory(Long id);
}

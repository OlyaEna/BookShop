package com.example.ex.service;

import com.example.ex.dto.CategoryDto;
import com.example.ex.dto.GenreDto;
import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;

import java.util.List;

public interface CategoryService {
   List<CategoryDto> findAll();
   Category saveCategory(CategoryDto categoryDto);
   void deleteCategory(Long id);
   void deleteById(Long id);
   void enableById(Long id);
   CategoryDto findById(Long id);
   Category update(CategoryDto categoryDto);
}

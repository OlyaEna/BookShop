package com.example.ex.service.impl;

import com.example.ex.dto.CategoryDto;
import com.example.ex.dto.GenreDto;
import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.repository.CategoryRepository;
import com.example.ex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = mapper(categories);
        return categoryDtoList;
    }

    private List<CategoryDto> mapper(List<Category> categories) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categories) {
            var categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDeleted(category.is_deleted());
            categoryDto.setActivated(category.is_activated());
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public Category saveCategory(CategoryDto categoryDto) {
        try {
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.set_activated(true);
            category.set_deleted(false);
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        category.set_activated(false);
        category.set_deleted(true);
        categoryRepository.save(category);

    }

    @Override
    public void enableById(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        category.set_activated(true);
        category.set_deleted(false);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDeleted(category.is_deleted());
        categoryDto.setActivated(category.is_activated());
        return categoryDto;
    }

    @Override
    public Category update(CategoryDto categoryDto) {
        try {
            Category category = categoryRepository.getReferenceById(categoryDto.getId());
            category.setName(categoryDto.getName());
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
package com.example.ex.services;

import com.example.ex.dto.CategoryDto;
import com.example.ex.dto.PublisherDto;
import com.example.ex.model.entity.Category;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.repository.CategoryRepository;
import com.example.ex.service.impl.CategoryServiceImpl;
import com.example.ex.service.impl.PublisherServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        this.categoryService
                = new CategoryServiceImpl(this.categoryRepository);

    }

    @Test
    void getAllCategories() {
        categoryService.findAll();
        verify(categoryRepository).findAll();
    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> list = new ArrayList<>();
        Category empOne = new Category(6L, "Glob", false, true);
        Category empTwo = new Category(7L, "Enter", false, true);
        Category empThree = new Category(8L, "Белпечать", false, true);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(categoryRepository.findAll()).thenReturn(list);

        //test
        List<CategoryDto> empList = categoryService.findAll();

        assertEquals(3, empList.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void getCategoryByIdTest() {
        when(categoryRepository.getReferenceById(1L)).thenReturn(new Category(1L, "OAP", false, true));

        CategoryDto pub = categoryService.findById(1L);

        assertEquals("OAP", pub.getName());
        assertEquals(false, pub.isDeleted());
        assertEquals(true, pub.isActivated());
    }


    @Test
    public void deleteCategoryByIdTest() {
        final Long id = 1L;
        categoryService.deleteCategory(1L);
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(2)).deleteById(id);

    }

    @Test
    public void createCategory() {
        Category category = new Category(1L, "OAP", false, true);
        categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();
    }

}

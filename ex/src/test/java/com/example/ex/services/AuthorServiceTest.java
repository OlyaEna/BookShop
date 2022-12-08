package com.example.ex.services;

import com.example.ex.dto.AuthorDto;
import com.example.ex.dto.CategoryDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Category;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.model.repository.CategoryRepository;
import com.example.ex.service.impl.AuthorServiceImpl;
import com.example.ex.service.impl.CategoryServiceImpl;
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

public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        this.authorService
                = new AuthorServiceImpl(this.authorRepository);

    }


    @Test
    void getAll() {
        authorService.findAll();
        verify(authorRepository).findAll();
    }

    @Test
    public void getAllTest() {
        List<Author> list = new ArrayList<>();
        Author empOne = new Author(6L, "Glob", false, true, null);
        Author empTwo = new Author(7L, "Enter", false, true, null);
        Author empThree = new Author(8L, "Белпечать", false, true, null);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(authorRepository.findAll()).thenReturn(list);

        //test
        List<AuthorDto> empList = authorService.findAll();

        assertEquals(3, empList.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        when(authorRepository.getReferenceById(1L)).thenReturn(new Author(1L, "OAP", false, true, null));

        AuthorDto pub = authorService.findById(1L);

        assertEquals("OAP", pub.getFio());
        assertEquals(false, pub.isDeleted());
        assertEquals(true, pub.isActivated());
    }


    @Test
    public void deleteByIdTest() {
        final Long id = 1L;
        authorService.deleteAuthor(1L);
        authorService.deleteAuthor(1L);
        verify(authorRepository, times(2)).deleteById(id);

    }

    @Test
    public void create() {
        Author author = new Author(1L, "OAP", false, true, null);
        authorRepository.save(author);
        assertThat(author.getId()).isNotNull();
    }

}

package com.example.ex.services;

import com.example.ex.dto.AuthorDto;
import com.example.ex.dto.GenreDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.model.repository.GenreRepository;
import com.example.ex.service.impl.AuthorServiceImpl;
import com.example.ex.service.impl.GenreServiceImpl;
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

public class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;
    @InjectMocks
    private GenreServiceImpl genreService;

    @BeforeEach
    void setUp() {
        this.genreService
                = new GenreServiceImpl(this.genreRepository);

    }


    @Test
    void getAll() {
        genreService.findAll();
        verify(genreRepository).findAll();
    }

    @Test
    public void getAllTest() {
        List<Genre> list = new ArrayList<>();
        Genre empOne = new Genre(6L, "Glob", false, true, null);
        Genre empTwo = new Genre(7L, "Enter", false, true, null);
        Genre empThree = new Genre(8L, "Белпечать", false, true, null);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(genreRepository.findAll()).thenReturn(list);

        //test
        List<GenreDto> empList = genreService.findAll();

        assertEquals(3, empList.size());
        verify(genreRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        when(genreRepository.getReferenceById(1L)).thenReturn(new Genre(1L, "OAP", false, true, null));

        GenreDto pub = genreService.findById(1L);

        assertEquals("OAP", pub.getName());
        assertEquals(false, pub.isDeleted());
        assertEquals(true, pub.isActivated());
    }


    @Test
    public void deleteByIdTest() {
        final Long id = 1L;
        genreService.deleteGenre(1L);
        genreService.deleteGenre(1L);
        verify(genreRepository, times(2)).deleteById(id);

    }

    @Test
    public void create() {
        Genre genre = new Genre(1L, "OAP", false, true, null);
        genreRepository.save(genre);
        assertThat(genre.getId()).isNotNull();
    }
}

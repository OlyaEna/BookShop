package com.example.ex.service;

import com.example.ex.dto.GenreDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.dto.SeriesDto;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();

    Genre saveGenre(GenreDto genreDto);

    void deleteGenre(Long id);

    GenreDto findById(Long id);

    void deleteById(Long id);

    void enableById(Long id);

    Genre update(GenreDto genreDto);


    List<Genre> listProducts(String title);

    Genre findGenreById(Long id);


}
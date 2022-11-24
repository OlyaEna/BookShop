package com.example.ex.service;

import com.example.ex.model.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre saveGenre(Genre genre);
//    Genre getGenreById(Long id);
    void deleteGenre(Long id);
//    Genre update(Genre category);
    Genre findById(Long id);

}
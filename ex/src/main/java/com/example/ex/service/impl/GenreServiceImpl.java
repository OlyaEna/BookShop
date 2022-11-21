package com.example.ex.service.impl;

import com.example.ex.model.entity.Genre;
import com.example.ex.model.repository.GenreRepository;
import com.example.ex.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
      return   genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        Genre genreSave=new Genre(genre.getName());
        return genreRepository.save(genreSave);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).get();
    }

    @Override
    public Genre update(Genre genre) {
        Genre genreUpdate = null;
        try {
            genreUpdate= genreRepository.findById(genre.getId()).get();
            genreUpdate.setName(genre.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return genreRepository.save(genreUpdate);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }



}
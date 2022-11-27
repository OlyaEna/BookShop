package com.example.ex.service.impl;

import com.example.ex.dto.GenreDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.dto.SeriesDto;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.Publisher;
import com.example.ex.model.entity.Series;
import com.example.ex.model.repository.GenreRepository;
import com.example.ex.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDto> genreDtoList = mapper(genres);
        return genreDtoList;
    }

    private List<GenreDto> mapper(List<Genre> genres) {
        List<GenreDto> genreDtoList = new ArrayList<>();
        for (Genre genre : genres) {
            var genreDto = new GenreDto();
            genreDto.setId(genre.getId());
            genreDto.setName(genre.getName());
            genreDto.setDeleted(genre.is_deleted());
            genreDto.setActivated(genre.is_activated());
            genreDtoList.add(genreDto);
        }
        return genreDtoList;
    }

    @Override
    public Genre saveGenre(GenreDto genreDto) {
        try {
            Genre genre = new Genre();
            genre.setName(genreDto.getName());
            genre.set_activated(true);
            genre.set_deleted(false);
            return genreRepository.save(genre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GenreDto findById(Long id) {
        Genre genre = genreRepository.getReferenceById(id);
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        genreDto.setDeleted(genre.is_deleted());
        genreDto.setActivated(genre.is_activated());
        return genreDto;
    }


    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        Genre genre = genreRepository.getReferenceById(id);
        genre.set_activated(false);
        genre.set_deleted(true);
        genreRepository.save(genre);

    }

    @Override
    public void enableById(Long id) {
        Genre genre = genreRepository.getReferenceById(id);
        genre.set_activated(true);
        genre.set_deleted(false);
        genreRepository.save(genre);
    }

    @Override
    public Genre update(GenreDto genreDto) {
        try {
            Genre genre = genreRepository.getReferenceById(genreDto.getId());
            genre.setName(genreDto.getName());
            return genreRepository.save(genre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


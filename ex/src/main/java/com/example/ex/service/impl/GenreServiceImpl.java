package com.example.ex.service.impl;

import com.example.ex.model.repository.GenreRepository;
import com.example.ex.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

}
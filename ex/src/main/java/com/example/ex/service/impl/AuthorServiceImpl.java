package com.example.ex.service.impl;

import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).get();
    }

}
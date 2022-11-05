package com.example.ex.service;

import com.example.ex.model.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
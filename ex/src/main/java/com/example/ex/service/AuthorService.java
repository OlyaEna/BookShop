package com.example.ex.service;

import com.example.ex.dto.AuthorDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

    AuthorDto findById(Long id);

    Author saveAuthor(AuthorDto authorDto);

    void deleteAuthor(Long id);

    void deleteById(Long id);

    void enableById(Long id);

    Author update(AuthorDto authorDto);

    Page<AuthorDto> page(int pageNo);
    Page<AuthorDto> search(int pageNo, String keyword);

}
package com.example.ex.service.impl;

import com.example.ex.dto.AuthorDto;
import com.example.ex.dto.GenreDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.AuthorRepository;
import com.example.ex.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtoList = mapper(authors);
        return authorDtoList;
    }

    private List<AuthorDto> mapper(List<Author> authors) {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author author : authors) {
            var authorDto = new AuthorDto();
            authorDto.setId(author.getId());
            authorDto.setFio(author.getFio());
            authorDto.setDeleted(author.is_deleted());
            authorDto.setActivated(author.is_activated());
            authorDtoList.add(authorDto);
        }
        return authorDtoList;
    }


    public AuthorDto findById(Long id) {
        Author author = authorRepository.getReferenceById(id);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFio(author.getFio());
        authorDto.setDeleted(author.is_deleted());
        authorDto.setActivated(author.is_activated());
        return authorDto;
    }

    @Override
    public Author saveAuthor(AuthorDto authorDto) {
        try {
            Author author = new Author();
            author.setFio(authorDto.getFio());
            author.set_activated(true);
            author.set_deleted(false);
            return authorRepository.save(author);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }


    @Override
    public void deleteById(Long id) {
        Author author = authorRepository.getReferenceById(id);
        author.set_deleted(true);
        author.set_activated(false);
        authorRepository.save(author);
    }

    @Override
    public void enableById(Long id) {
        Author author = authorRepository.getReferenceById(id);
        author.set_activated(true);
        author.set_deleted(false);
        authorRepository.save(author);
    }

    @Override
    public Author update(AuthorDto authorDto) {
        try {
            Author author = authorRepository.getReferenceById(authorDto.getId());
            author.setFio(authorDto.getFio());
            return authorRepository.save(author);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<AuthorDto> page(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<AuthorDto> authors = mapper(authorRepository.findAll());
        Page<AuthorDto> authorPages = toPage(authors, pageable);
        return authorPages;
    }

    @Override
    public Page<AuthorDto> search(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<AuthorDto> authors = mapper(authorRepository.searchList(keyword));
        Page<AuthorDto> authorPages = toPage(authors, pageable);
        return authorPages;
    }
    private Page toPage(List<AuthorDto> list , Pageable pageable){
        if(pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }


}
package com.example.ex.model.repository;

import com.example.ex.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByFioContainingIgnoreCaseOrderByFio(String fio);

    @Override
    List<Author> findAll();
}
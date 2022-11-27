package com.example.ex.model.repository;

import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();

    @Query("select a from Author a")
    Page<Author> page(Pageable pageable);

    @Query("select a from Author a where  a.fio like  %?1%")
    Page<Author> search(String keyword, Pageable pageable);

    @Query("select a from Author a where  a.fio like  %?1%")
    List<Author> searchList(String keyword);

}
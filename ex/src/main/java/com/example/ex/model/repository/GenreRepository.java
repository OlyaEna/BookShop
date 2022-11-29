package com.example.ex.model.repository;

import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByNameContainingIgnoreCaseOrderByName(String name);


    @Query("select g from Genre g where g.name like  %?1%")
    List<Genre> searchList(String keyword);

    List<Genre> findByName (String title);


}
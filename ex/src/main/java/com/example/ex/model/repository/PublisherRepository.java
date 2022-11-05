package com.example.ex.model.repository;

import com.example.ex.model.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    List<Publisher> findByNameContainingIgnoreCaseOrderByName(String name);
}
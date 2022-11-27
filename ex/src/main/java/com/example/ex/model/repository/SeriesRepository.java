package com.example.ex.model.repository;

import com.example.ex.model.entity.Role;
import com.example.ex.model.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository  extends JpaRepository<Series, Long> {
}

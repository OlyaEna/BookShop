package com.example.ex.model.repository;

import com.example.ex.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, Long> {
}

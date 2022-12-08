package com.example.ex.model.repository;

import com.example.ex.model.entity.Message;
import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUser (User user);

}

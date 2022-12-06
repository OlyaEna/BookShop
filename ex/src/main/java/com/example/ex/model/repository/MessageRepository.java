package com.example.ex.model.repository;

import com.example.ex.model.entity.Message;
import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUser (User user);


}

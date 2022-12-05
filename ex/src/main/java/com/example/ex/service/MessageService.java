package com.example.ex.service;

import com.example.ex.dto.MessageDto;
import com.example.ex.model.entity.Message;
import com.example.ex.model.entity.User;

public interface MessageService {
    Message save(MessageDto messageDto, User user);
}

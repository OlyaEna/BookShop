package com.example.ex.service;

import com.example.ex.dto.MessageDto;
import com.example.ex.model.entity.Message;
import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.User;

import java.security.Principal;
import java.util.List;

public interface MessageService {
    Message save(MessageDto messageDto, User user);

    List<MessageDto> getMessagesByUser(User user);
    List<MessageDto> getUserMessage(Principal principal);
    MessageDto getMessageById(Long id);

}

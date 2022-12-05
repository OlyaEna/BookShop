package com.example.ex.service.impl;

import com.example.ex.dto.MessageDto;
import com.example.ex.model.entity.Message;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.MessageRepository;
import com.example.ex.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Message save(MessageDto messageDto, User user) {
        try {
            Message message = new Message();
            message.setId(messageDto.getId());
            message.setCreated(messageDto.getCreated());
            message.setEmail(messageDto.getEmail());
            message.setFirstName(messageDto.getFirstName());
            message.setLastName(messageDto.getLastName());
            message.setPhoneNumber(messageDto.getPhoneNumber());
            message.setMessage(messageDto.getMessage());
            message.setTopic(messageDto.getTopic());
            message.setUser(messageDto.getUser());
            message.setUser(user);
           return messageRepository.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

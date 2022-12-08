package com.example.ex.service.impl;

import com.example.ex.dto.MessageDto;
import com.example.ex.dto.ProductDto;
import com.example.ex.model.entity.Message;
import com.example.ex.model.entity.Order;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.MessageRepository;
import com.example.ex.service.MessageService;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

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
            message.set_read(false);
            message.setUser(user);
            return messageRepository.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<MessageDto> transfer(List<Message> messages) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (Message message : messages) {
            MessageDto messageDto = new MessageDto();
            mapper(message, messageDto);
            messageDtoList.add(messageDto);
        }
        return messageDtoList;

    }

    @Override
    public List<MessageDto> getMessagesByUser(User user) {
        List<Message> messages = messageRepository.findAllByUser(user);
        List<MessageDto> messageDtoList = transfer(messages);
        return messageDtoList;
    }

    public List<MessageDto> getUserMessage(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return getMessagesByUser(user);
    }

    @Override
    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.getReferenceById(id);
        MessageDto messageDto = new MessageDto();
        mapper(message, messageDto);
        return messageDto;
    }

    private void mapper(Message message, MessageDto messageDto) {
        messageDto.setId(message.getId());
        messageDto.setMessage(message.getMessage());
        messageDto.setEmail(message.getEmail());
        messageDto.setCreated(message.getCreated());
        messageDto.setUser(message.getUser());
        messageDto.setTopic(message.getTopic());
        messageDto.setFirstName(message.getFirstName());
        messageDto.setLastName(message.getLastName());
        messageDto.setPhoneNumber(message.getPhoneNumber());
        messageDto.set_read(message.is_read());
    }

    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        List<MessageDto> messageDtoList = transfer(messages);
        return messageDtoList;
    }

    @Override
    public void readById(Long id) {
       Message message=messageRepository.getReferenceById(id);
        if (message != null) {
            if (message.is_read()) {
                message.set_read(false);
            } else {
                message.set_read(true);
            }
        }
        messageRepository.save(message);
    }

}

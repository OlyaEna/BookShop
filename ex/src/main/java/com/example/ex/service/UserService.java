package com.example.ex.service;

import com.example.ex.dto.ProductDto;
import com.example.ex.dto.UserDto;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void saveUser(MultipartFile imageProduct, UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    void banUser(Long id);

    public UserDto getById(Long id);

    User update(MultipartFile imageProduct, UserDto userDto);


}

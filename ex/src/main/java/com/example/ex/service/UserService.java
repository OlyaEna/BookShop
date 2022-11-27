package com.example.ex.service;

import com.example.ex.dto.UserDto;
import com.example.ex.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

}

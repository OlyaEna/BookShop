package com.example.ex.dto;

import com.example.ex.model.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String phoneNumber;
    private String email;
    private boolean active;
    private List<Role> roles;

}

package com.example.ex.service.impl;

import com.example.ex.dto.ProductDto;
import com.example.ex.dto.UserDto;
import com.example.ex.model.entity.Product;
import com.example.ex.model.entity.Role;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.RoleRepository;
import com.example.ex.model.repository.UserRepository;
import com.example.ex.service.UserService;
import com.example.ex.utils.ImageUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ImageUpload imageUpload;


    @Override
    public void saveUser(MultipartFile imageProduct, UserDto userDto) {
        try {
            User user = new User();
            if (imageProduct == null) {
                user.setImage(null);
            } else {
                imageUpload.uploadImage(imageProduct);
                user.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            user.setId(user.getId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setActive(true);
            user.setCountry(userDto.getCountry());
            user.setCity(userDto.getCity());
            user.setAddress(userDto.getAddress());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            Role role = roleRepository.findByName("ROLE_USER");
            if (role == null) {
                role = checkRoleExist();
            }
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public User update(MultipartFile imageProduct, UserDto userDto) {
        try {
            User user = userRepository.getReferenceById(userDto.getId());
            if (imageProduct == null) {
                user.setImage(user.getImage());
            } else {
                if (imageUpload.checkExisted(imageProduct) == false) {
                    imageUpload.uploadImage(imageProduct);
                }
                user.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            user.setId(user.getId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setActive(userDto.isActive());
            user.setCountry(userDto.getCountry());
            user.setCity(userDto.getCity());
            user.setAddress(userDto.getAddress());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setActive(user.isActive());
        userDto.setCountry(user.getCountry());
        userDto.setCity(user.getCity());
        userDto.setAddress(user.getAddress());
        userDto.setImage(user.getImage());
        return userDto;
    }

    public void banUser(Long id) {
        User user = userRepository.getReferenceById(id);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        }
        userRepository.save(user);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.getReferenceById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setActive(user.isActive());
        userDto.setCountry(user.getCountry());
        userDto.setCity(user.getCity());
        userDto.setAddress(user.getAddress());
        userDto.setImage(user.getImage());
        return userDto;
    }



//
//
//    public User findUserById(Long userId) {
//        Optional<User> userFromDb = userRepository.findById(userId);
//        return userFromDb.orElse(new User());
//    }
//

}

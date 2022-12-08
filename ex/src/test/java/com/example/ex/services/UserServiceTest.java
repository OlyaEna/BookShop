package com.example.ex.services;

import com.example.ex.dto.SeriesDto;
import com.example.ex.dto.UserDto;
import com.example.ex.exception.ApiRequestException;
import com.example.ex.model.entity.Role;
import com.example.ex.model.entity.Series;
import com.example.ex.model.entity.User;
import com.example.ex.model.repository.RoleRepository;
import com.example.ex.model.repository.UserRepository;
import com.example.ex.service.impl.PublisherServiceImpl;
import com.example.ex.service.impl.SeriesServiceImpl;
import com.example.ex.service.impl.UserServiceImpl;
import com.example.ex.utils.ImageUpload;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private  UserRepository userRepository;
    private  User user;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        user = new User(1L, "12345", "ten@mail.com", "Olya", "Olya", "1234", true, "city", "country",
                "address", "img", null);
    }


    @Test
    void shouldSavedUserSuccessFully() {
        userRepository.save(new User(1L, "12345", "ten@mail.com", "Olya", "Olya", "1234", true, "city", "country",
                "address", "img", null));
        userRepository.save(new User(2L, "12345", "teddn@mail.com", "Olya", "Olya", "12534", true, "city", "country",
                "address", "img", null));
        verify(userRepository, times(2)).save(any(User.class));

    }

    @Test
    void getUsersTest() {
        // when
        userService.findAllUsers();

        // then
        verify(userRepository).findAll();
    }

    @Test
    void showUserByIncorrectEmail() {
        User user = new User(1L, "12345", "ten@mail.com", "Olya", "Olya", "1234", true, "city", "country",
                "address", "img", null);
        String email = "email@gmail.com1";
        Optional<User> userOptional = null;
        try {
            userOptional = Optional.ofNullable(userService.findUserByEmail(email));
            if(userOptional.isPresent()) {
                System.out.println(userOptional.toString());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assertFalse(userOptional.isPresent());
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.getReferenceById(1L)).thenReturn(new User(1L, "12345", "ten@mail.com",
                "Olya", "Olya", "1234", true, "city", "country",
                "address", "img", null));

        UserDto userDto = userService.getById(1L);

        assertEquals("ten@mail.com", userDto.getEmail());
        assertEquals("Olya", userDto.getFirstName());
        assertEquals("Olya", userDto.getLastName());
        assertEquals("1234", userDto.getPhoneNumber());
        assertEquals(true, userDto.isActive());
        assertEquals("city", userDto.getCountry());
        assertEquals("country", userDto.getCity());
        assertEquals("address", userDto.getAddress());
        assertEquals("img", userDto.getImage());

    }







}

package com.example.ex.dto;

import com.example.ex.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private LocalDateTime created;
    @NotEmpty(message = "Message should not be empty")
    private String message;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;
    @NotEmpty(message = "Phone number should be empty")
    private String phoneNumber;
    @NotEmpty(message = "Topic should not be empty")
    private String topic;
    private User user;
    private boolean is_read;

}

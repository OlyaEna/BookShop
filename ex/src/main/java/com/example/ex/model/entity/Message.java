package com.example.ex.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime created;
    @Column(columnDefinition = "text")
    private String message;
    @Column(name = "email")
    private String email;
    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(columnDefinition = "text")
    private String topic;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private boolean is_read;

}

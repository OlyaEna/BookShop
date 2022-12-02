package com.example.ex.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    @CreationTimestamp
    private LocalDateTime created;
    private Date deliveryDate;
    private double totalPrice;
    private String orderStatus;
    private String notes;
    private String country;
    private String city;
    private String address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany( mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @CreationTimestamp
//    private LocalDateTime created;
//    @UpdateTimestamp
//    private LocalDateTime updated;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User users;
//    private BigDecimal sum;
//    private String address;
//    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
//    private List<OrderDetails> orderDetails;




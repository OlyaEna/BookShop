package com.example.ex.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;
    private String ISBN;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "series_id")
    private Series series;
    private LocalDateTime dateOfCreated;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private boolean is_deleted;
    private boolean is_activated;
    private boolean novelty;
    private boolean bestseller;
    private boolean selection;


    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }


}
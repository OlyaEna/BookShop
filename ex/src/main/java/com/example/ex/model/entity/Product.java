package com.example.ex.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "products")
@Getter
@Setter
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
    //    @ManyToMany(mappedBy = "products")
//    private  List <Author> authors;
//    @ManyToMany(mappedBy = "products")
//    private List <Genre> genres;
//    @ManyToMany(mappedBy = "products")
//    private List <Bucket> buckets;
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



    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }



}
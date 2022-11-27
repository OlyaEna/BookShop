package com.example.ex.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;
    private boolean is_deleted;
    private boolean is_activated;

    //    @ManyToMany
//    @JoinTable(
//            name = "author_product",
//            joinColumns = @JoinColumn(name = "author_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private List<Product> products;
//
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Product> products;

}
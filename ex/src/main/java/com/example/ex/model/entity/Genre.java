package com.example.ex.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean is_deleted;
    private boolean is_activated;


//    @ManyToMany
//    @JoinTable(
//            name = "genre_product",
//            joinColumns = @JoinColumn(name = "genre_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private List<Product> products;

    @OneToMany(mappedBy="genre", fetch=FetchType.EAGER)
    private List<Product> products;

}
package com.example.ex.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="series", fetch=FetchType.EAGER)
    private List<Product> products;

}

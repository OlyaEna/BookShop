package com.example.ex.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean is_deleted;
    private boolean is_activated;

    @OneToMany(mappedBy="series", fetch=FetchType.EAGER)
    private List<Product> products;



    public Series(Long id, String name, boolean is_deleted, boolean is_activated) {
        this.id = id;
        this.name = name;
        this.is_deleted = is_deleted;
        this.is_activated = is_activated;
    }
}

package com.example.ex.dto;

import com.example.ex.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    //    private List <Genre> genres;
//    private List <Author> authors;
    private Genre genre;
    private Author  author;
    private Series series;
    private Publisher publisher;
    private String image;
    private Category category;
    //    private boolean deleted;
//    private boolean activated;
    private String ISBN;

}

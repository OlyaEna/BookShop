package com.example.ex.dto;

import com.example.ex.model.entity.Author;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Publisher;
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
    private List <Genre> genres;
    private List <Author> authors;
    private Publisher publishers;
    private String image;
//    private boolean deleted;
//    private boolean activated;
    private String ISBN;
}

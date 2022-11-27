package com.example.ex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDto {
    private Long id;
    private String name;
    private boolean deleted;
    private boolean activated;

}



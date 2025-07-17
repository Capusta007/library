package com.example.library.dto;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}

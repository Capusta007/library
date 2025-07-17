package com.example.library.dto;

import lombok.Data;

@Data
public class BookRequestDto {
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}

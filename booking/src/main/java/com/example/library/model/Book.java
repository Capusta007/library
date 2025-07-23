package com.example.library.model;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String author;
}

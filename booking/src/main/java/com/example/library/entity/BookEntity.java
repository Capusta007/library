package com.example.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(unique = true, name = "Isbn")
    private String isbn;
    @Column(name = "Title")
    private String title;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "Description")
    private String description;
    @Column(name = "Author")
    private String author;
}

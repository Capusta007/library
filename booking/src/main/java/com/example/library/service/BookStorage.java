package com.example.library.service;

import com.example.library.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookStorage {

    List<BookEntity> getAllBooks();

    Optional<BookEntity> getBookById(Long id);

    Optional<BookEntity> getBookByIsbn(String isbn);

    BookEntity createBook(BookEntity book);

    BookEntity updateBook(Long id, BookEntity updatedBook);

    void deleteBookById(Long id);
}

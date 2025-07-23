package com.example.library.storage;

import com.example.library.model.Book;
import jakarta.persistence.EntityExistsException;

import java.util.List;
import java.util.Optional;

public interface BookStorage {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Optional<Book> getBookByIsbn(String isbn);

    Book createBook(Book book) throws EntityExistsException;

    Optional<Book> updateBook(Long id, Book updatedBook);

    void deleteBookById(Long id);
}

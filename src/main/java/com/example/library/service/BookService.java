package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book getBookByIsbn(String isbn);

    Book createBook(Book book);

    Book updateBook(Long id, Book updatedBook);

    void deleteBookById(Long id);
}

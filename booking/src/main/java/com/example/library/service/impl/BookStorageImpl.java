package com.example.library.service.impl;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookStorage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookStorageImpl implements BookStorage {
    private final BookRepository repository;

    @Override
    public List<BookEntity> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<BookEntity> getBookByIsbn(String isbn) {
        return repository.findBookByIsbn(isbn);
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        if (repository.existsBookByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("A book with this ISBN already exists");
        }
        return repository.save(book);
    }

    @Override
    public BookEntity updateBook(Long id, BookEntity updatedBook) throws EntityNotFoundException {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedBook.getTitle());
                    existing.setAuthor(updatedBook.getAuthor());
                    existing.setIsbn(updatedBook.getIsbn());
                    existing.setDescription(updatedBook.getDescription());
                    existing.setGenre(updatedBook.getGenre());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }
}

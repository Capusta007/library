package com.example.library.storage.impl;

import com.example.library.entity.BookEntity;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.storage.BookStorage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookStorageImpl implements BookStorage {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll().stream().map(bookMapper::map).toList();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return repository.findById(id).map(bookMapper::map);
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return repository.findBookByIsbn(isbn).map(bookMapper::map);
    }

    @Override
    public Book createBook(Book book) throws EntityExistsException {
        if (repository.existsBookByIsbn(book.getIsbn())) {
            throw new EntityExistsException("A book with this ISBN already exists");
        }
        BookEntity bookEntity = bookMapper.toEntity(book);
        return bookMapper.map(repository.save(bookEntity));
    }

    @Override
    public Optional<Book> updateBook(Long id, Book updatedBook) throws EntityNotFoundException {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedBook.getTitle());
                    existing.setAuthor(updatedBook.getAuthor());
                    existing.setIsbn(updatedBook.getIsbn());
                    existing.setDescription(updatedBook.getDescription());
                    existing.setGenre(updatedBook.getGenre());
                    return bookMapper.map(repository.save(existing));
                });
    }

    @Override
    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }
}

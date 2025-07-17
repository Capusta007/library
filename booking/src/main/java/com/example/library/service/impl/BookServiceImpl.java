package com.example.library.service.impl;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book getBookByIsbn(String isbn) throws EntityNotFoundException {
        return repository.findBookByIsbn(isbn).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book createBook(Book book) {
        if(repository.existsBookByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("A book with this ISBN already exists");
        }
        return repository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) throws EntityNotFoundException{
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

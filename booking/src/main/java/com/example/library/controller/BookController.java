package com.example.library.controller;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.BookEntity;
import com.example.library.mapper.BookMapper;
import com.example.library.service.BookStorage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookStorage bookStorage;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        List<BookEntity> books = bookStorage.getAllBooks();
        return bookMapper.toDtoList(books);
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable(name = "id") long id) {
        BookEntity book = bookStorage.getBookById(id).orElseThrow(EntityNotFoundException::new);
        return bookMapper.toDto(book);
    }

    @GetMapping("/isbn/{isbn}")
    public BookResponseDto getBookByIsbn(@PathVariable(name = "isbn") String isbn) {
        BookEntity book = bookStorage.getBookByIsbn(isbn).orElseThrow(EntityNotFoundException::new);
        return bookMapper.toDto(book);
    }

    @PostMapping
    public BookResponseDto createBook(@RequestBody @Valid BookRequestDto dto) {
        BookEntity book = bookMapper.toEntity(dto);
        BookEntity savedBook = bookStorage.createBook(book);
        return bookMapper.toDto(savedBook);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable(name = "id") Long id, @RequestBody @Valid BookRequestDto dto) {
        BookEntity book = bookMapper.toEntity(dto);
        BookEntity updatedBook = bookStorage.updateBook(id, book);
        return bookMapper.toDto(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable(name = "id") Long id) {
        bookStorage.deleteBookById(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFound(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}

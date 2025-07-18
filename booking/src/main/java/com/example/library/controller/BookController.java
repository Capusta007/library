package com.example.library.controller;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return bookMapper.toDtoList(books);
    }

    @PostMapping
    public BookResponseDto createBook(@RequestBody @Valid BookRequestDto dto) {
        Book book = bookMapper.toEntity(dto);
        Book savedBook = bookService.createBook(book);
        return bookMapper.toDto(savedBook);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable(name = "id") Long id, @RequestBody @Valid BookRequestDto dto) {
        Book book = bookMapper.toEntity(dto);
        Book updatedBook = bookService.updateBook(id, book);
        return bookMapper.toDto(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable(name = "id") Long id) {
        bookService.deleteBookById(id);
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

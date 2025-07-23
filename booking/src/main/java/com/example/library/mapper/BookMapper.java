package com.example.library.mapper;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.BookEntity;
import com.example.library.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book map(BookRequestDto dto);

    BookResponseDto toDto(Book book);

    BookEntity toEntity(Book user);

    Book map(BookEntity bookEntity);

    List<BookResponseDto> toDtoList(List<Book> books);
}

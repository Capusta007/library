package com.example.library.mapper;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequestDto dto);

    BookResponseDto toDto(Book book);

    List<BookResponseDto> toDtoList(List<Book> books);
}

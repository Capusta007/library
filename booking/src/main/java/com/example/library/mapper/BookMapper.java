package com.example.library.mapper;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.BookEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity toEntity(BookRequestDto dto);

    BookResponseDto toDto(BookEntity book);

    List<BookResponseDto> toDtoList(List<BookEntity> books);
}

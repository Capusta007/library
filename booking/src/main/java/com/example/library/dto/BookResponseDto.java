package com.example.library.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Response to get a book")
@Builder(toBuilder = true)
public record BookResponseDto(
        Long id,
        String isbn,
        String title,
        String genre,
        String description,
        String author
) {
}

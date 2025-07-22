package com.example.library.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record BookRequestDto(
        String isbn,
        String title,
        String genre,
        String description,
        String author
) {
}

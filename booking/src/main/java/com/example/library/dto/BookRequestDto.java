package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Schema(description = "Request to create a book")
@Builder(toBuilder = true)
public record BookRequestDto(
        @NotBlank(message = "ISBN must not be null")
        @Schema(example = "978-3-16-148410-0")
        String isbn,

        @NotBlank(message = "Title must not be blank")
        @Schema(example = "Harry Potter")
        String title,

        @NotBlank(message = "Genre must not be blank")
        @Schema(example = "Fantasy")
        String genre,

        @Size(max = 1000, message = "Description is too long")
        @Schema(example = "An epic fantasy novel.")
        String description,

        @Pattern(
                regexp = "^[A-ZА-ЯЁ][a-zа-яё]+( [A-ZА-ЯЁ][a-zа-яё]+)*$",
                message = "Incorrect author name"
        )
        @NotBlank(message = "Author must not be blank")
        @Schema(example = "Joanne Rowling")
        String author
) {
}

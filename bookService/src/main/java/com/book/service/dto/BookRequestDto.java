package com.book.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class BookRequestDto {

    private String title;
    private String author;
    private String genre;
    private String isbn;
    private int availableCopies;
    private int totalCopies;

}

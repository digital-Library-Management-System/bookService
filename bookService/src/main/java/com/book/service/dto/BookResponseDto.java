package com.book.service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int availableCopies;
    private int totalCopies;

}

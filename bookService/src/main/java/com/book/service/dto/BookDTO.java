package com.book.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BookDTO {

    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int availableCopies;
    private int totalCopies;

}

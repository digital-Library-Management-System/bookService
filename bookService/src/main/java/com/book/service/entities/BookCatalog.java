package com.book.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class BookCatalog {

    @SequenceGenerator(
            name  = "catalog_sequence",
            sequenceName = "catalog_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "catalog_sequence"
    )
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int availableCopies;
    private int totalCopies;

    public BookCatalog(String title, String author, String isbn, String genre, int availableCopies, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }
}

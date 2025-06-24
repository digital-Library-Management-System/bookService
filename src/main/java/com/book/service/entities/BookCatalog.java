package com.book.service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
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

}

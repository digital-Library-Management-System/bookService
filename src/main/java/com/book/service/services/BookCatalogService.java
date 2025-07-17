package com.book.service.services;

import com.book.service.dto.BookRequestDto;
import com.book.service.entities.BookCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface BookCatalogService {

    BookCatalog addBook(BookCatalog book);

    BookCatalog getBookById(Long id);

    Page<BookCatalog> getAllBooks(Pageable pageable);

    List<BookCatalog> getAll();

    BookCatalog updateBook(Long id,  BookRequestDto dto);

    void deleteBook(Long id);

}

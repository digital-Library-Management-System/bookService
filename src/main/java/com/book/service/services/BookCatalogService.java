package com.book.service.services;

import com.book.service.dto.BookDto;
import com.book.service.entities.BookCatalog;


import java.util.List;

public interface BookCatalogService {

    BookCatalog addBook(BookCatalog book);

    BookCatalog getBookById(Long id);

    List<BookCatalog> getAllBooks(int page, int size);

    BookCatalog updateBook(Long id,  BookDto dto);

    void deleteBook(Long id);

}

package com.book.service.services;

import com.book.service.dto.BookRequestDto;
import com.book.service.dto.BookResponseDto;


import java.util.List;

public interface BookCatalogService {
    BookRequestDto addBook(BookRequestDto dto);
    BookResponseDto getBookById(Long id);
    List<BookResponseDto> getAllBooks(int page, int size);
    BookRequestDto updateBook(Long id, BookRequestDto dto);
    BookResponseDto deleteBook(Long id);

}

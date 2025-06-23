package com.book.service.services;

import com.book.service.dto.BookRequestDto;
import com.book.service.dto.BookResponseDto;
import com.book.service.entities.BookCatalog;
import com.book.service.mappers.BookMapper;
import com.book.service.repositories.BookCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Objects;


@Service @RequiredArgsConstructor
public class BookCatalogServiceImpl implements BookCatalogService {

    private final BookCatalogRepository bookCatalogRepository;
    private final BookMapper bookMapper;

    public BookRequestDto addBook(BookRequestDto dto) {
        BookCatalog book = bookMapper.toEntity(dto);

        if (bookCatalogRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "book already exists");
        }

        BookCatalog savedBook = bookCatalogRepository.save(book);
        return bookMapper.toRequestDto(savedBook);
    }


    public BookResponseDto getBookById(Long id) {

        BookCatalog book = bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return bookMapper.toResponseDto(book);

    }

    public  List<BookResponseDto>  getAllBooks(int page, int size) {

        List<BookCatalog> catalog = bookCatalogRepository.findAll(PageRequest.of(page - 1, size)).getContent();

        if(catalog.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found");
        if (page < 1 || page > 2) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the page must be between 1 and 2");
        else if (size > 6) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the size must be between 1 and 6");
        return bookMapper.toResponseDtoList(catalog);

    }

    public BookRequestDto updateBook(Long id, BookRequestDto dto) {
        BookCatalog book = bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookMapper.updateBookFromDto(dto, book);

        BookCatalog updated = bookCatalogRepository.save(book);
        return bookMapper.toRequestDto(updated);
    }

    public BookResponseDto deleteBook(Long id) {
        BookCatalog book = bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
         bookCatalogRepository.deleteById(id);
         return bookMapper.toResponseDto(book);


    }

}

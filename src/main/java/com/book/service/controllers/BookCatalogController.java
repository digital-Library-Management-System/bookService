package com.book.service.controllers;

import com.book.service.dto.BookRequestDto;
import com.book.service.dto.BookResponseDto;
import com.book.service.entities.BookCatalog;
import com.book.service.mappers.BookMapper;
import com.book.service.services.BookCatalogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/bookService")
@RequiredArgsConstructor
public class BookCatalogController {

    private final BookCatalogServiceImpl bookCatalogServiceImpl;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto dto) {

        BookCatalog book = bookMapper.toEntity(dto);
        BookCatalog saveBook  = bookCatalogServiceImpl.addBook(book);
        BookResponseDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {

        BookCatalog saveBook = bookCatalogServiceImpl.getBookById(id);
        BookResponseDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);

    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(@RequestParam int page, @RequestParam int size) {

        List<BookCatalog> saveBook = bookCatalogServiceImpl.getAllBooks(page, size);
        List<BookResponseDto> bookResponse  = bookMapper.toDtoList(saveBook);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {

        BookCatalog saveBook  = bookCatalogServiceImpl.updateBook(id, dto);
        BookResponseDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        bookCatalogServiceImpl.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");

    }


}

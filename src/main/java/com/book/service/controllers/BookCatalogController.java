package com.book.service.controllers;

import com.book.service.dto.BookRequestDto;
import com.book.service.dto.BookResponseDto;
import com.book.service.entities.BookCatalog;
import com.book.service.services.BookCatalogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/bookService/")
@RequiredArgsConstructor
public class BookCatalogController {

    private final BookCatalogServiceImpl bookCatalogServiceImpl;

    @PostMapping
    public ResponseEntity<BookRequestDto> createBook(@RequestBody BookRequestDto dto) {

        BookRequestDto saveBook  = bookCatalogServiceImpl.addBook(dto);
        return ResponseEntity.status(HttpStatus.OK).body(saveBook);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {

        bookCatalogServiceImpl.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogServiceImpl.getBookById(id));

    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(@RequestParam int page, @RequestParam int size) {

        bookCatalogServiceImpl.getAllBooks(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogServiceImpl.getAllBooks(page, size));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRequestDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {

        BookRequestDto saveBook  = bookCatalogServiceImpl.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(saveBook);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogServiceImpl.deleteBook(id));

    }


}

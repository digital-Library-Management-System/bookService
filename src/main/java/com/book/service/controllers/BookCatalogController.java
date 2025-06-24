package com.book.service.controllers;

import com.book.service.dto.BookDto;
import com.book.service.entities.BookCatalog;
import com.book.service.mappers.BookMapper;
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
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookDto dto) {

        BookCatalog book = bookMapper.toEntity(dto);
        BookCatalog saveBook  = bookCatalogServiceImpl.addBook(book);
        bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body("Book created successfully");

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {

        BookCatalog saveBook = bookCatalogServiceImpl.getBookById(id);
        BookDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);

    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam int page, @RequestParam int size) {

        List<BookCatalog> saveBook = bookCatalogServiceImpl.getAllBooks(page, size);
        List<BookDto> bookResponse = bookMapper.toDtoList(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDto dto) {

        BookCatalog saveBook  = bookCatalogServiceImpl.updateBook(id, dto);
        bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        bookCatalogServiceImpl.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");

    }


}

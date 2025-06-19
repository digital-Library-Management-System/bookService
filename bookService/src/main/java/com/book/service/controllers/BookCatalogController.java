package com.book.service.controllers;

import com.book.service.dto.BookDTO;
import com.book.service.entities.BookCatalog;
import com.book.service.services.BookCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/bookService/")
public class BookCatalogController {

    private final BookCatalogService bookCatalogService;

    public BookCatalogController(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;


    }

    @PostMapping(path="create")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO dto) {
        BookDTO saveBook  = bookCatalogService.addBook(dto);
        return ResponseEntity.status(HttpStatus.OK).body(saveBook);

    }

    @GetMapping(path="getBookById/{id}")
    public ResponseEntity<BookCatalog> getBookById(@PathVariable Long id) {

        bookCatalogService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogService.getBookById(id));
    }

    @GetMapping(path = "getAllBooksByPagination")
    public ResponseEntity<List<BookCatalog>> getAllBooks(@RequestParam int page, @RequestParam int size) {

        bookCatalogService.getAllBooks(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogService.getAllBooks(page, size));

    }

    @PutMapping(path="update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {

        BookDTO saveBook  = bookCatalogService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(saveBook);
    }


}

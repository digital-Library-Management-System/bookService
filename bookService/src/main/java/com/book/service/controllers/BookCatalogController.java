package com.book.service.controllers;

import com.book.service.dto.BookDTO;
import com.book.service.entities.BookCatalog;
import com.book.service.services.BookCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path="/bookService/")
public class BookCatalogController {

    private final BookCatalogService bookCatalogService;
    public BookCatalogController(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @PostMapping(path="create")
    public ResponseEntity<String> createBook(@RequestBody BookDTO dto) {
        bookCatalogService.addBook(dto);
        return ResponseEntity.ok("Book added successfully");

    }

}

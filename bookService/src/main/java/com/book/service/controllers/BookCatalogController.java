package com.book.service.controllers;

import com.book.service.dto.BookRequestDTO;
import com.book.service.dto.BookResponseDTO;
import com.book.service.entities.BookCatalog;
import com.book.service.services.BookCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/bookService/")
@RequiredArgsConstructor
public class BookCatalogController {

    private final BookCatalogService bookCatalogService;

    @PostMapping(path="create")
    public ResponseEntity<BookRequestDTO> createBook(@RequestBody BookRequestDTO dto) {

        BookRequestDTO saveBook  = bookCatalogService.addBook(dto);
        return ResponseEntity.status(HttpStatus.OK).body(saveBook);

    }

    @GetMapping(path="getBookById/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {

        bookCatalogService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogService.getBookById(id));
    }

    @GetMapping(path = "getAllBooksByPagination")
    public ResponseEntity<List<BookCatalog>> getAllBooks(@RequestParam int page, @RequestParam int size) {

        bookCatalogService.getAllBooks(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogService.getAllBooks(page, size));

    }

    @PutMapping(path="update/{id}")
    public ResponseEntity<BookRequestDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO dto) {

        BookRequestDTO saveBook  = bookCatalogService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(saveBook);
    }

    @DeleteMapping(path ="delete/{id}")
    public ResponseEntity<BookResponseDTO> deleteBook(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookCatalogService.deleteBook(id));

    }


}

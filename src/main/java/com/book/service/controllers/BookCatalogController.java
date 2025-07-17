package com.book.service.controllers;

import com.book.service.dto.BookRequestDto;
import com.book.service.dto.BookResponseDto;
import com.book.service.entities.BookCatalog;
import com.book.service.mappers.BookMapper;
import com.book.service.services.BookCatalogService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/bookService")
@RequiredArgsConstructor
public class BookCatalogController {

    private final BookMapper bookMapper;
    private final BookCatalogService bookCatalogService;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto dto) {

        BookCatalog book = bookMapper.toEntity(dto);
        BookCatalog saveBook  = bookCatalogService.addBook(book);
        BookResponseDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {

        BookCatalog saveBook = bookCatalogService.getBookById(id);
        BookResponseDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @GetMapping
    public  Page<BookResponseDto> getAllBooks(Pageable pageable) {

        Page<BookCatalog> saveBook = bookCatalogService.getAllBooks(pageable);
        List<BookResponseDto> listOfBook = bookMapper.toDtoList(saveBook.getContent());

        return new PageImpl<>(listOfBook, pageable, listOfBook.size());
    }

    @GetMapping("/all")
    public List<BookResponseDto> getAll() {
        List<BookCatalog> bookList = bookCatalogService.getAll();
        return bookMapper.toDtoList(bookList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {

        BookCatalog saveBook  = bookCatalogService.updateBook(id, dto);
        BookResponseDto bookResponse = bookMapper.toDto(saveBook);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        bookCatalogService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }


}

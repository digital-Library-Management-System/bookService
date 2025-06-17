package com.book.service.services;

import com.book.service.dto.BookDTO;
import com.book.service.entities.BookCatalog;
import com.book.service.repositories.BookCatalogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class BookCatalogService {

    private final BookCatalogRepository bookCatalogRepository;
    private final ModelMapper modelMapper;


    public BookCatalogService(BookCatalogRepository bookCatalogRepository, ModelMapper modelMapper) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.modelMapper = modelMapper;


    }

    public void addBook(BookDTO dto) {
        BookCatalog book = modelMapper.map(dto, BookCatalog.class);
        Optional<BookCatalog> bookCatalogOptional = bookCatalogRepository.findByIsbn(book.getIsbn());

       if (bookCatalogOptional.isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "book already exists");
       bookCatalogRepository.save(book);
    }

    public BookCatalog getBookById(Long id) {
        return bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

    }
}

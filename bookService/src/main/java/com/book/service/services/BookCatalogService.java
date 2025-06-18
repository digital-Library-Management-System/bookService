package com.book.service.services;

import com.book.service.dto.BookDTO;
import com.book.service.entities.BookCatalog;
import com.book.service.repositories.BookCatalogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
public class BookCatalogService {

    private final BookCatalogRepository bookCatalogRepository;
    private final ModelMapper modelMapper;


    public BookCatalogService(BookCatalogRepository bookCatalogRepository, ModelMapper modelMapper) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.modelMapper = modelMapper;


    }

    public BookDTO addBook(BookDTO dto) {
        BookCatalog book = modelMapper.map(dto, BookCatalog.class);

        if (bookCatalogRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "book already exists");
        }

        BookCatalog savedBook = bookCatalogRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }


    public BookCatalog getBookById(Long id) {

        return bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

    }

    public List<BookCatalog> getAllBooks(int page, int size) {

        List<BookCatalog> catalog = bookCatalogRepository.findAll(PageRequest.of(page - 1, size)).getContent();

        if(catalog.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found");
        if (page < 1 || page > 2) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the page must be between 1 and 2");
        else if (size > 6) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the size must be between 1 and 6");
        return catalog;

    }
}

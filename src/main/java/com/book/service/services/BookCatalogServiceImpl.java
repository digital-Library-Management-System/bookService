package com.book.service.services;

import com.book.service.dto.BookRequestDto;
import com.book.service.entities.BookCatalog;
import com.book.service.mappers.BookMapper;
import com.book.service.repositories.BookCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
@RequiredArgsConstructor
public class BookCatalogServiceImpl implements BookCatalogService {

    private final BookCatalogRepository bookCatalogRepository;
    private final BookMapper bookMapper;

    public BookCatalog addBook(BookCatalog book) {

        if (bookCatalogRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "book already exists");
        }

        return bookCatalogRepository.save(book);
    }


    public BookCatalog getBookById(Long id) {

        return bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    public Page<BookCatalog> getAllBooks(Pageable pageable) {

        return bookCatalogRepository.findAll(pageable);
    }

    public List<BookCatalog> getAll(){
        return bookCatalogRepository.findAll();
    }

    public BookCatalog updateBook(Long id, BookRequestDto dto) {

        BookCatalog book = bookCatalogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        BookCatalog updatedBook = bookMapper.updateBookFromDto(dto, book);

        return bookCatalogRepository.save(updatedBook);
    }


    public void deleteBook(Long id) {
       boolean exists = bookCatalogRepository.existsById(id);

       if (exists) {
            bookCatalogRepository.deleteById(id);
       }else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
       }
    }

}

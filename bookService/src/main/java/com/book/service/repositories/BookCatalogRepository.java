package com.book.service.repositories;

import com.book.service.entities.BookCatalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookCatalogRepository extends CrudRepository<BookCatalog, Long> {


    Optional<BookCatalog> findByIsbn(String isbn);
}

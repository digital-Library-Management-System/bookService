package com.book.service.repositories;

import com.book.service.entities.BookCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookCatalogRepository extends JpaRepository<BookCatalog, Long> {

    Optional<BookCatalog> findByIsbn(String isbn);
    Page<BookCatalog> findAll(Pageable pageable);


}

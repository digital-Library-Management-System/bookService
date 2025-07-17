package tests;

import com.book.service.entities.BookCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.book.service.repositories.BookCatalogRepository;
import com.book.service.services.BookCatalogServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceGetAllBookMethodTest {

    @Mock
    private BookCatalogRepository bookCatalogRepository;

    @InjectMocks
    private BookCatalogServiceImpl bookCatalogServiceImpl;

    @Test
    @Order(1)
    @DisplayName("should return a paginated list when books exist")
    void shouldReturnPaginatedListWhenBooksExist() {

        Pageable pageable = PageRequest.of(0, 6);
        List<BookCatalog> books = List.of(
                new BookCatalog(1L, "Book 1", "Author 1", "ISBN1", "Genre1", 3, 5),
                new BookCatalog(2L, "Book 2", "Author 2", "ISBN2", "Genre2", 2, 4)
        );

        Page<BookCatalog> page = new PageImpl<>(books, pageable, books.size());

        when(bookCatalogRepository.findAll(pageable)).thenReturn(page);
        Page<BookCatalog> result = bookCatalogServiceImpl.getAllBooks(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals("Book 1", result.getContent().get(0).getTitle());

        assertAll("assertions group",
                () -> assertEquals(2, result.getContent().size(), "should be equal 2"),
                () -> assertEquals("Book 1", result.getContent().get(0).getTitle(),"should be equal Book1"),
                () -> assertEquals("Book 2", result.getContent().get(1).getTitle(),"should be equal Book2"));

        verify(bookCatalogRepository).findAll(pageable);
    }


    @Test
    @Order(2)
    @DisplayName("should return a empty list when books do not exist")
    void shouldReturnEmptyListWhenBooksDoNotExist(){

        Pageable pageable = PageRequest.of(0, 6);
        List<BookCatalog> books = List.of();
        Page<BookCatalog> page = new PageImpl<>(books, pageable, 0);

        when(bookCatalogRepository.findAll(pageable)).thenReturn(page);
        Page<BookCatalog> result = bookCatalogServiceImpl.getAllBooks(pageable);

        assertEquals(0, result.getContent().size());

        verify(bookCatalogRepository).findAll(pageable);
    }

}

package tests;


import com.book.service.entities.BookCatalog;
import com.book.service.repositories.BookCatalogRepository;
import com.book.service.services.BookCatalogServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceGetAllMethodTest {

    @Mock
    private BookCatalogRepository bookCatalogRepository;

    @InjectMocks
    private BookCatalogServiceImpl bookCatalogServiceImpl;

    @Test
    @Order(1)
    @DisplayName("should return a list of books")
    public void shouldReturnListOfBooksWhenBooksExist() {

        when(bookCatalogRepository.findAll()).thenReturn(List.of(new BookCatalog()));
        List<BookCatalog> listOfBook = bookCatalogServiceImpl.getAll();
        assertEquals(listOfBook, bookCatalogRepository.findAll(),"should return a list of books");

        verify(bookCatalogRepository, times(2)).findAll();

    }

    @Test
    @Order(2)
    @DisplayName("should return a empty list")
    void shouldReturnEmptyListWhenNoInput() {

        when(bookCatalogRepository.findAll()).thenReturn(List.of());
        List<BookCatalog> listOfBook = bookCatalogServiceImpl.getAll();
        assertEquals(0, listOfBook.size(), "should return empty list");

        verify(bookCatalogRepository , times(1)).findAll();
    }

}

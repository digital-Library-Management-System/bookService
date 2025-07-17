package tests;



import com.book.service.entities.BookCatalog;
import com.book.service.repositories.BookCatalogRepository;
import com.book.service.services.BookCatalogServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CONFLICT;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

    @Mock
    private BookCatalogRepository bookCatalogRepository;

    @InjectMocks
    private BookCatalogServiceImpl bookCatalogServiceImpl;


    Long noExistentId;

    @BeforeEach
    void setUp(){
        noExistentId = 99L;
    }


    @Test
    @Order(1)
    @DisplayName("should return a book")
    void shouldReturnBookWhenIdExists(){

        BookCatalog bookCatalog = new BookCatalog("Digital Shadows",
                "Miles Carter",
                "978-0-525-55532-1",
                "Cyberpunk",
                2,
                 5);

        when(bookCatalogRepository.findByIsbn(bookCatalog.getIsbn()))
                .thenReturn(Optional.empty());

        when(bookCatalogRepository.save(any(BookCatalog.class)))
                .thenReturn(bookCatalog);

        BookCatalog result = bookCatalogServiceImpl.addBook(bookCatalog);

        assertEquals(bookCatalog.getIsbn(), result.getIsbn());
        verify(bookCatalogRepository).findByIsbn(bookCatalog.getIsbn());
    }

    @Test
    @Order(1)
    @DisplayName("should throw an error")
    void shouldThrowAnErrorWhenIdDoesNotExist(){

        BookCatalog bookCatalog = new BookCatalog("Digital Shadows",
                "Miles Carter",
                "978-0-525-55532-1",
                "Cyberpunk",
                2,
                5);

        when(bookCatalogRepository.findByIsbn(bookCatalog.getIsbn()))
                .thenReturn(Optional.of(bookCatalog));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> bookCatalogServiceImpl.addBook(bookCatalog),
                "should throw an Exception");
        assertTrue(exception.getMessage().contains("409 CONFLICT"), "should throw an Exception");

        verify(bookCatalogRepository).findByIsbn(bookCatalog.getIsbn());


    }


}

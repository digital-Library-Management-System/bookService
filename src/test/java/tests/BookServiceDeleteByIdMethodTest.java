package tests;


import com.book.service.repositories.BookCatalogRepository;
import com.book.service.services.BookCatalogServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceDeleteByIdMethodTest {

    @Mock
    private BookCatalogRepository bookCatalogRepository;

    @InjectMocks
    private BookCatalogServiceImpl bookCatalogServiceImpl;

    Long id;
    Long nonExistentId;

    @BeforeEach
    void setUp() {
        id = 1L;
        nonExistentId = 99L;
    }


    @Test
    @Order(1)
    @DisplayName("should delete a book when id exists")
    void shouldDeleteBookWhenIdExists() {

        when(bookCatalogRepository.existsById(id)).thenReturn(true);
        bookCatalogServiceImpl.deleteBook(id);

        verify(bookCatalogRepository).deleteById(id);
    }

    @Test
    @Order(2)
    @DisplayName("should throw a exception  when id does not exist")
    void shouldThrowExceptionWhenIdDoesNotExist() {

        when(bookCatalogRepository.existsById(nonExistentId)).thenReturn(false);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> bookCatalogServiceImpl.deleteBook(nonExistentId),
                "should throw a exception");
        assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode(), "should throw a status code");
    }

}

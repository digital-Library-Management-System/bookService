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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceGetBookByIdMethodTest {

    @Mock
    private BookCatalogRepository bookCatalogRepository;

    @InjectMocks
    private BookCatalogServiceImpl bookCatalogServiceImpl;

    Long id;
    Long nonExistentId;

    @BeforeEach
    public void setup() {
        id = 1L;
        nonExistentId = 99L;
    }

    @Test
    @Order(1)
    @DisplayName("should return a book when id exists")
    void shouldReturnBookWhenIdExists() {

        BookCatalog bookCatalog = new BookCatalog(id,"Digital Shadows",
                "Miles Carter",
                "978-0-525-55532-1",
                "Cyberpunk",
                2,
                5);

        when(bookCatalogRepository.findById(id)).thenReturn(Optional.of(bookCatalog));
        BookCatalog bookCatalogReturned = bookCatalogServiceImpl.getBookById(id);
        assertEquals(bookCatalog.getId(), bookCatalogReturned.getId(),"Ids should match");

        verify(bookCatalogRepository).findById(id);
    }

    @Test
    @Order(2)
    @DisplayName("should throw an error when id does not exist")
    void shouldThrowAnErrorWhenIdDoesNotExist() {
        BookCatalog bookCatalog = new BookCatalog(nonExistentId,"Digital Shadows",
                "Miles Carter",
                "978-0-525-55532-1",
                "Cyberpunk",
                2,
                5);

        when(bookCatalogRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        ResponseStatusException responseStatus = assertThrows(ResponseStatusException.class, () -> bookCatalogServiceImpl.getBookById(nonExistentId),
                "should throw an exception when id does not exist");
        assertEquals(HttpStatus.NOT_FOUND, responseStatus.getStatusCode(), "should throw a status code");

        verify(bookCatalogRepository).findById(nonExistentId);
    }

}

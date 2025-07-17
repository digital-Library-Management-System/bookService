package tests;


import com.book.service.dto.BookRequestDto;
import com.book.service.entities.BookCatalog;
import com.book.service.mappers.BookMapper;
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
public class BookServiceUpdateBookMethodTest {

    @Mock
    private BookCatalogRepository bookCatalogRepository;

    @Mock
    private BookMapper bookMapper;

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
    @DisplayName("should update a book when id exists")
    void shouldUpdateBookWhenIdExists() {
        BookCatalog book = new BookCatalog(id,"Digi Shadows","Miles John",
                "978-0-525-55532-3","Cyberpunk",2,7);

        BookRequestDto bookRequestDto = new BookRequestDto("Galaxian Explosion", "Kara",
                "977-0-525-55532-2","Non-fiction",4,10);

        book.setTitle("Galaxian Explosion");
        book.setAuthor("Kara");
        book.setIsbn("977-0-525-55532-2");
        book.setGenre("Non-fiction");
        book.setAvailableCopies(4);
        book.setTotalCopies(10);

        when(bookCatalogRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookMapper.updateBookFromDto(bookRequestDto, book)).thenReturn(book);
        when(bookCatalogRepository.save(book)).thenReturn(book);

        BookCatalog updatedBook = bookCatalogServiceImpl.updateBook(id, bookRequestDto);

        assertAll("assertions group",
                () -> assertEquals("Galaxian Explosion", updatedBook.getTitle()),
                () -> assertEquals("Kara", updatedBook.getAuthor()),
                () -> assertEquals("977-0-525-55532-2", updatedBook.getIsbn()),
                () -> assertEquals("Non-fiction", updatedBook.getGenre()),
                () -> assertEquals(4, updatedBook.getAvailableCopies()),
                () -> assertEquals(10, updatedBook.getTotalCopies())
        );

        verify(bookCatalogRepository).findById(id);
        verify(bookMapper).updateBookFromDto(bookRequestDto, book);
        verify(bookCatalogRepository).save(book);
    }

    @Test
    @Order(2)
    @DisplayName("should throw an exception when id does not exist")
    void shouldThrowExceptionWhenIdDoesNotExist() {

        when(bookCatalogRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> bookCatalogServiceImpl.getBookById(nonExistentId),
                "should throw an exception when id does not exist");

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "should throw an status code");

    }

}

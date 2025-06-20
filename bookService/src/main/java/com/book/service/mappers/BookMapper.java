package com.book.service.mappers;

import com.book.service.dto.BookRequestDTO;
import com.book.service.dto.BookResponseDTO;
import com.book.service.entities.BookCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDTO bookCatalogToBookResponseDTO(BookCatalog book);
    BookCatalog bookResponseDTOToBookCatalog(BookResponseDTO bookResponseDTO);

    @Mapping(target ="id", ignore = true)
    BookCatalog bookRequestDTOToBookCatalog(BookRequestDTO bookRequestDTO);
    BookRequestDTO bookCatalogToBookRequestDTO(BookCatalog book);



}

package com.book.service.mappers;

import com.book.service.dto.BookDto;
import com.book.service.entities.BookCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(BookCatalog book);

    List<BookDto> toDtoList(List<BookCatalog> books);

    @Mapping(target ="id", ignore = true)
    BookCatalog toEntity(BookDto bookDTO);

    @Mapping(target = "id", ignore = true)
    void updateBookFromDto(BookDto dto, @MappingTarget BookCatalog book);

}

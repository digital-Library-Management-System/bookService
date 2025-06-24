package com.book.service.mappers;

import com.book.service.dto.BookRequestDto;
import com.book.service.dto.BookResponseDto;
import com.book.service.entities.BookCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDto toResponseDto(BookCatalog book);

    List<BookResponseDto> toResponseDtoList(List<BookCatalog> books);

    @Mapping(target ="id", ignore = true)
    BookCatalog toEntity(BookRequestDto bookRequestDTO);

    BookRequestDto toRequestDto(BookCatalog book);

    void updateBookFromDto(BookRequestDto dto, @MappingTarget BookCatalog book);

}

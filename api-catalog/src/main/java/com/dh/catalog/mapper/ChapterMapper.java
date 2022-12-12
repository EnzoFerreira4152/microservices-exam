package com.dh.catalog.mapper;

import com.dh.catalog.dto.series.ChapterDto;
import com.dh.catalog.model.series.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChapterMapper {
    Chapter toChapterEntity(ChapterDto dto);
    ChapterDto toChapterDto(Chapter entity);

    List<Chapter> toChapterEntity(Iterable<ChapterDto> dtos);
    List<ChapterDto> toChapterDto(Iterable<Chapter> entities);
}

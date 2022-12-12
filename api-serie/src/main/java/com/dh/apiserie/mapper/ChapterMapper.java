package com.dh.apiserie.mapper;

import com.dh.apiserie.dto.ChapterDto;
import com.dh.apiserie.model.Chapter;
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

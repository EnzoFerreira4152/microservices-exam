package com.dh.catalog.mapper;

import com.dh.catalog.dto.series.SeasonDto;
import com.dh.catalog.model.series.Season;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ChapterMapper.class})
public interface SeasonMapper {
    Season ToSerieEntity(SeasonDto dto);
    SeasonDto ToSerieDto(Season entity);

    List<Season> toSeasonEntity(Iterable<SeasonDto> dtos);
    List<SeasonDto> toSeasonDto(Iterable<Season> entities);

}

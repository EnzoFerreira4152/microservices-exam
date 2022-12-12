package com.dh.catalog.mapper;

import com.dh.catalog.dto.series.SerieDto;
import com.dh.catalog.model.series.Serie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {SeasonMapper.class})
public interface SerieMapper {
    Serie ToSerieEntity(SerieDto dto);
    SerieDto ToSerieDto(Serie entity);

    List<Serie> toSerieEntity(Iterable<SerieDto> dtos);
    List<SerieDto> toSerieDto(Iterable<Serie> entities);

}

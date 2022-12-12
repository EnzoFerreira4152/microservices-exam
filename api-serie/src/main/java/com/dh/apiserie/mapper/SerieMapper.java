package com.dh.apiserie.mapper;

import com.dh.apiserie.dto.SerieDto;
import com.dh.apiserie.model.Serie;
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

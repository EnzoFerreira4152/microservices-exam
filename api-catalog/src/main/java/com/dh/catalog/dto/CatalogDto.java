package com.dh.catalog.dto;

import com.dh.catalog.dto.series.SerieDto;
import java.util.List;

public record CatalogDto(
        String genre,
        List<MovieDto> movies,
        List<SerieDto> series
) {}

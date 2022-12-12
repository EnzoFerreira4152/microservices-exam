package com.dh.catalog.dto.series;

import java.util.List;

public record SerieDto(
        String id,
        String name,
        String genre,
        List<SeasonDto> seasons
) {}

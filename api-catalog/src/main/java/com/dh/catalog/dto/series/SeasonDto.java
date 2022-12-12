package com.dh.catalog.dto.series;

import java.util.List;

public record SeasonDto(
        String id,
        Integer seasonNumber,
        List<ChapterDto> chapters
) {}

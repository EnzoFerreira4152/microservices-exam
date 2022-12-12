package com.dh.apiserie.dto;

import java.util.List;

public record SeasonDto(String id, Integer seasonNumber, List<ChapterDto> chapters) {
}

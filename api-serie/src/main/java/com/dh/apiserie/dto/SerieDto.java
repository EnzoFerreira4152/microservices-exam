package com.dh.apiserie.dto;

import java.util.List;

public record SerieDto(String id, String name, String genre, List<SeasonDto> seasons) {
}

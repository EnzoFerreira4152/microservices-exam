package com.dh.catalog.dto;

public record MovieDto(
        Long id,
        String name,
        String genre,
        String urlStream
) {}

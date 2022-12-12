package com.dh.apiserie.controller;

import com.dh.apiserie.dto.SerieDto;
import com.dh.apiserie.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping
    ResponseEntity<List<SerieDto>> getAll() {
        return ResponseEntity.ok(serieService.getAll());
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<SerieDto>> getSerieByGenre(@PathVariable String genre){
        return ResponseEntity.ok(serieService.getSeriesBygGenre(genre));
    }

    @PostMapping
    ResponseEntity<SerieDto> createNewSerie(@RequestBody SerieDto serie) {
        return ResponseEntity.ok(serieService.createSerie(serie));
    }

}

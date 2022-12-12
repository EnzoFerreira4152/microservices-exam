package com.dh.catalog.client;

import com.dh.catalog.dto.series.SerieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "api-serie")
public interface SerieServiceClient {

    @GetMapping("/api/v1/series/{genre}")
    List<SerieDto> getSerieByGenre(@PathVariable String genre);

}

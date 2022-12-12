package com.dh.apiserie.service;

import com.dh.apiserie.dto.SerieDto;
import com.dh.apiserie.event.NewSerieProducer;
import com.dh.apiserie.mapper.SerieMapper;
import com.dh.apiserie.mapper.SerieMapperImpl;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;
    private final NewSerieProducer newSerieProducer;

    @Inject
    private SerieMapper mapper;

    public SerieService(SerieRepository repository, NewSerieProducer newSerieProducer) {
        this.repository = repository;
        this.newSerieProducer = newSerieProducer;
    }

    public List<SerieDto> getAll(){
        return mapper.toSerieDto(repository.findAll());
    }

    public List<SerieDto> getSeriesBygGenre(String genre) {
        List<Serie> series = repository.findAllByGenre(genre);
        return mapper.toSerieDto(series);
    }

    public SerieDto createSerie(SerieDto serieDto) {
        Serie serieSaved = repository.save(mapper.ToSerieEntity(serieDto));
        newSerieProducer.execute(serieSaved);
        return mapper.ToSerieDto(serieSaved);
    }
}

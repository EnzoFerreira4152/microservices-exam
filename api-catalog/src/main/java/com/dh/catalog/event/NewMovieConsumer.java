package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConsumerConfiguration;
import com.dh.catalog.dto.MovieDto;
import com.dh.catalog.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NewMovieConsumer {

    CatalogService catalogService;

    public NewMovieConsumer(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RabbitListener(queues = RabbitMQConsumerConfiguration.NEW_MOVIE_QUEUE)
    public void messageReceiveNewMovie(MovieDto movie){
        System.out.println("Mensaje recibido > Nueva Película " + movie.name());
        catalogService.saveNewMovie(movie);
        System.out.println("Película guardada");
    }

}

package com.dh.movie.event;

import com.dh.movie.config.RabbitMQProducerConfiguration;
import com.dh.movie.dto.MovieDto;
import com.dh.movie.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewMovieProducer {

    private final RabbitTemplate rabbitTemplate;

    public NewMovieProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Movie movie){
        System.out.println("Enviando mensaje > Nueva película: " + movie.getName());
        rabbitTemplate
                .convertAndSend(
                        RabbitMQProducerConfiguration.EXCHANGE_NAME,
                        RabbitMQProducerConfiguration.ROUTING_KEY_NEW_MOVIE,
                        movie
                );
    }

}

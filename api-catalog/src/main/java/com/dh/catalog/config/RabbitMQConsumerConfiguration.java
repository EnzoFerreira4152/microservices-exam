package com.dh.catalog.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfiguration {
    public static final String EXCHANGE_NAME = "newProductExchange";
    public static final String NEW_MOVIE_QUEUE = "newMovieQueue";
    public static final String NEW_SERIE_QUEUE = "newSerieQueue";
    public static final String ROUTING_KEY_MOVIE = "new.product.movie";
    public static final String ROUTING_KEY_SERIE = "new.product.serie";

    @Bean
    public TopicExchange newProductExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue newMovieQueue(){
        return new Queue(NEW_MOVIE_QUEUE, true);
    }

    @Bean
    public Queue newSerieQueue(){
        return new Queue(NEW_SERIE_QUEUE, true);
    }

    @Bean
    public Binding bindingNewMovieQueueToNewProductExchange(){
        return BindingBuilder
                .bind(newMovieQueue()) //Queue
                .to(newProductExchange()) //Exchange
                .with(ROUTING_KEY_MOVIE); //Routing Key
    }

    @Bean
    public Binding bindingNewSerieQueueToNewProductExchange(){
        return BindingBuilder
                .bind(newSerieQueue()) //Queue
                .to(newProductExchange()) //Exchange
                .with(ROUTING_KEY_SERIE); //Routing Key
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory factory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

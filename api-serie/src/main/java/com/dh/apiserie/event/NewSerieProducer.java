package com.dh.apiserie.event;

import com.dh.apiserie.config.RabbitMQProducerConfiguration;
import com.dh.apiserie.model.Serie;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewSerieProducer {

    private final RabbitTemplate rabbitTemplate;

    public NewSerieProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Serie serie){
        System.out.println("Enviando mensaje > Nueva serie: " + serie.getName());
        rabbitTemplate
                .convertAndSend(
                        RabbitMQProducerConfiguration.EXCHANGE_NAME,
                        RabbitMQProducerConfiguration.ROUTING_KEY_NEW_MOVIE,
                        serie
                );
    }

}

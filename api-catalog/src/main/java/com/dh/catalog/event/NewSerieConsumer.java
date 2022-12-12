package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConsumerConfiguration;
import com.dh.catalog.dto.series.SerieDto;
import com.dh.catalog.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NewSerieConsumer {

    CatalogService catalogService;

    public NewSerieConsumer(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RabbitListener(queues = RabbitMQConsumerConfiguration.NEW_SERIE_QUEUE)
    public void messageReceiveNewSerie(SerieDto serie){
        System.out.println("Mensaje recibido > Nueva Serie " + serie.name());
        catalogService.saveNewSerie(serie);
        System.out.println("Serie guardada");
    }

}

package br.com.dio.warehouse.service.impl;


import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.service.IProductChangeAvailabilityProducer;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IProductChangeAvailabilityProducerImpl implements IProductChangeAvailabilityProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKeyName;

    public IProductChangeAvailabilityProducerImpl(final RabbitTemplate rabbitTemplate,
                                                  @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                  final String exchangeName,
                                                  @Value("${spring.rabbitmq.routing-key.product-change-availability}")
                                                  final String routingKeyName) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyName = routingKeyName;
    }

    @Override
    public void notifyStatusChange(StockStatusMessage message) {
        rabbitTemplate.convertAndSend(exchangeName,routingKeyName,message);
    }
}

package br.com.dio.warehouse.service.impl;


import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;
import br.com.dio.warehouse.repository.StockRepository;
import br.com.dio.warehouse.service.IProductChangeAvailabilityProducer;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SotckServiceImpl implements IStockService {

    private StockRepository repository;
    private final IProductQueryService productService;
    private final IProductChangeAvailabilityProducer producer;

    @Override
    public StockEntity save(StockEntity entity) {
        entity.setProduct(productService.findById(entity.getProduct().getId()));
        return repository.save(entity);
    }

    @Override
    public void release(UUID id) {

        changeStatus(id, StockStatus.AVAILABLE);

    }

    @Override
    public void inactive(UUID id) {

        changeStatus(id, StockStatus.UNAVAILABLE);
    }

    @Override
    public void changeStatus(UUID id, StockStatus status) {

        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(status);
        repository.save(entity);
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(),status));

    }
}

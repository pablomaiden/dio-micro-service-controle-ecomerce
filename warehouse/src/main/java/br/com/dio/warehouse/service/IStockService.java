package br.com.dio.warehouse.service;

import br.com.dio.warehouse.entity.StockEntity;
import br.com.dio.warehouse.entity.StockStatus;

import java.util.UUID;

public interface IStockService {
    StockEntity save(final StockEntity entity);
    void release(UUID id);
    void inactive(UUID id);
    void changeStatus(UUID id, final StockStatus status);


}

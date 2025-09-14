package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.ProductStoreFrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.mapper.IProductMapper;
import br.com.dio.warehouse.repository.ProductRepository;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository repository;
    private IStockService stockService;
    private IProductQueryService queryService;
    private RestClient storeFrontRestClient;
    private IProductMapper mapper;

    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDto(entity);
        saveStoreFront(dto);
        return entity;
    }

    private void saveStoreFront(ProductStoreFrontSaveDTO dto) {

        storeFrontRestClient.post()
                .uri("/products/")
                .body(dto)
                .retrieve()
                .body(ProductStoreFrontSaveDTO.class);

    }

    @Override
    public void purchase(UUID id) {
        var entity = queryService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);

        if(stock.isUnavailable()){
            stockService.changeStatus(stock.getId(), stock.getStatus());
        }

    }
}

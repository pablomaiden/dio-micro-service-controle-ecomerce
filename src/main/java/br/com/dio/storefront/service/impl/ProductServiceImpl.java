package br.com.dio.storefront.service.impl;

import br.com.dio.storefront.dto.ProductInfoDTO;
import br.com.dio.storefront.entity.ProductEntity;
import br.com.dio.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return null;
    }

    @Override
    public void changeActived(UUID id, boolean active) {

    }

    @Override
    public List<ProductEntity> findAllActive() {
        return List.of();
    }

    @Override
    public ProductInfoDTO findInfo(UUID id) {
        return null;
    }

    @Override
    public void purchase(UUID id) {

    }
}

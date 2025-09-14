package br.com.dio.warehouse.mapper;

import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.response.ProductDetailResponse;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.dto.ProductStoreFrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    @Mapping(target = "price", ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toSavedResponse(final ProductEntity entity);

    ProductDetailResponse toDetailResponse(final ProductEntity entity);

    @Mapping(target = "active", ignore = true)
    ProductStoreFrontSaveDTO toDto(final ProductEntity entity);

}


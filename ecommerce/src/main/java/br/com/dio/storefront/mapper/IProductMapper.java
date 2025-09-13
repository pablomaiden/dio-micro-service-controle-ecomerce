package br.com.dio.storefront.mapper;

import br.com.dio.storefront.controller.request.ProductSaveRequest;
import br.com.dio.storefront.controller.response.ProductAvailableResponse;
import br.com.dio.storefront.controller.response.ProductDetailResponse;
import br.com.dio.storefront.controller.response.ProductSavedResponse;
import br.com.dio.storefront.dto.ProductDetailsDTO;
import br.com.dio.storefront.dto.ProductInfoDTO;
import br.com.dio.storefront.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {
    ProductInfoDTO toDto(final ProductEntity entity, final BigDecimal price);

    ProductSavedResponse toResponse(final ProductEntity entity);

    List<ProductAvailableResponse> toResponse(final List<ProductEntity> entits);

    ProductDetailResponse toResponse(ProductInfoDTO dto);

    @Mapping(target = "active", constant = "false")
    ProductEntity toEntity(final ProductSaveRequest request);



}

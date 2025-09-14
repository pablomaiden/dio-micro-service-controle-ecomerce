package br.com.dio.warehouse.controller;


import br.com.dio.warehouse.controller.request.ProductSaveRequest;
import br.com.dio.warehouse.controller.request.StockSaveRequest;
import br.com.dio.warehouse.controller.response.ProductSavedResponse;
import br.com.dio.warehouse.controller.response.StockSavedResponse;
import br.com.dio.warehouse.mapper.IProductMapper;
import br.com.dio.warehouse.mapper.IStockMapper;
import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("stocks")
@AllArgsConstructor
public class StockController {

    private final IStockService service;
    private final IStockMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    StockSavedResponse save(@RequestBody final StockSaveRequest request){
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toSavedResponse(entity);
    }

    @PutMapping("{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void active(@PathVariable final UUID id){
        service.release(id);
    }

    @DeleteMapping("{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inactive(@PathVariable final UUID id){
        service.inactive(id);
    }


}

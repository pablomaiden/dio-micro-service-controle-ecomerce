package br.com.dio.storefront.service;

import br.com.dio.storefront.dto.StockStatusMessage;

public interface IProductChangeAvaibilityConsumer {

    void receive(final StockStatusMessage message);

}

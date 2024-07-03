package application.controllers;

import application.api.ProductOrderResource;
import application.purchase.ProductOrderAssembler;
import application.purchase.ProductOrderDto;
import application.purchase.adapter.persistence.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductOrderController implements ProductOrderResource {

    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderAssembler productOrderAssembler;

    @Autowired
    public ProductOrderController(ProductOrderRepository productOrderRepository, ProductOrderAssembler productOrderAssembler) {
        this.productOrderRepository = productOrderRepository;
        this.productOrderAssembler = productOrderAssembler;
    }

    @Override
    public List<ProductOrderDto> getAllProductOrders(String purchaseId) {
        List<ProductOrderDto> all = new ArrayList<>();

        productOrderRepository
                .findAllByPurchaseId(purchaseId)
                .forEach(o -> all.add(productOrderAssembler.createDto(o)));

        return all;
    }

}

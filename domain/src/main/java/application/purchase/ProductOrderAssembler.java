package application.purchase;

public interface ProductOrderAssembler {

    ProductOrderDto createDto(ProductOrder purchase);

    ProductOrder createModel(Purchase purchase, ProductOrderDto productOrderDto);
}


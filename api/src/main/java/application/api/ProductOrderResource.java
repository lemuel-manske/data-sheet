package application.api;

import application.purchase.ProductOrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface ProductOrderResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/{purchaseId}/product-order")
    List<ProductOrderDto> getAllProductOrders(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/purchase/{purchaseId}/product-order")
    ProductOrderDto addProductOrder(@PathVariable(name = "purchaseId") String purchaseId,
                                    @RequestBody ProductOrderDto productOrderDto);

}

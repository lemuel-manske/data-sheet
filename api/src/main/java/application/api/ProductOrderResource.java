package application.api;

import application.purchase.ProductOrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface ProductOrderResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/{purchaseId}/product-order")
    List<ProductOrderDto> getAll(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/product-order/{productOrderId}")
    ProductOrderDto getById(@PathVariable(name = "productOrderId") String productOrderId);


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/purchase/{purchaseId}/product-order")
    ProductOrderDto add(@PathVariable(name = "purchaseId") String purchaseId,
                        @RequestBody ProductOrderDto productOrderDto);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/purchase/product-order/{productOrderId}")
    void delete(@PathVariable(name = "productOrderId") String productOrderId);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/purchase/product-order/{productOrderId}")
    ProductOrderDto update(@PathVariable(name = "productOrderId") String productOrderId,
                           @RequestBody ProductOrderDto productOrderDto);
}

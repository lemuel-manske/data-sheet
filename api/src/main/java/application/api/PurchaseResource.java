package application.api;

import application.purchase.ProductOrderDto;
import application.purchase.PurchaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface PurchaseResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/{purchaseId}")
    PurchaseDto getPurchaseById(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase")
    List<PurchaseDto> getAllPurchases();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/purchase")
    PurchaseDto createPurchase(@RequestBody PurchaseDto purchase);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/purchase/{purchaseId}")
    void deletePurchase(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/{purchaseId}/product-order")
    List<ProductOrderDto> getAllPurchaseProductOrders(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/product-order/{productOrderId}")
    ProductOrderDto getProductOrderById(@PathVariable(name = "productOrderId") String productOrderId);


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/purchase/{purchaseId}/product-order")
    ProductOrderDto addProductOrderToPurchase(@PathVariable(name = "purchaseId") String purchaseId,
                                              @RequestBody ProductOrderDto productOrderDto);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/purchase/product-order/{productOrderId}")
    void deleteProductOrder(@PathVariable(name = "productOrderId") String productOrderId);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/purchase/product-order/{productOrderId}")
    ProductOrderDto updateProductOrder(@PathVariable(name = "productOrderId") String productOrderId,
                                       @RequestBody ProductOrderDto productOrderDto);
}

package application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ProductOrderNotFound.ORDER_NOT_FOUND)
public class ProductOrderNotFound extends RuntimeException {

    public static final String ORDER_NOT_FOUND = "Order not found";

    public ProductOrderNotFound() {
        super(ProductOrderNotFound.ORDER_NOT_FOUND);
    }
}

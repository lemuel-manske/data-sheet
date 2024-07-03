package application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = PurchaseNotFound.PURCHASE_NOT_FOUND)
public class PurchaseNotFound extends RuntimeException {

    public static final String PURCHASE_NOT_FOUND = "Purchase not found";

    public PurchaseNotFound() {
        super(PurchaseNotFound.PURCHASE_NOT_FOUND);
    }
}

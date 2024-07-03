package application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import application.purchase.PurchaseDto;

public interface PurchaseResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/{purchaseId}")
    PurchaseDto getById(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/purchase")
    PurchaseDto create(@RequestBody PurchaseDto purchase);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/purchase/{purchaseId}")
    void delete(@PathVariable(name = "purchaseId") String purchaseId);
}

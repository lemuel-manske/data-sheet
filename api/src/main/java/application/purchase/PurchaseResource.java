package application.purchase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PurchaseResource {

    @GetMapping("/purchase/{purchaseId}")
    PurchaseDto getById(@PathVariable(name = "purchaseId") String purchaseId);

    @PostMapping("/purchase")
    PurchaseDto create(@RequestBody PurchaseDto purchase);

}

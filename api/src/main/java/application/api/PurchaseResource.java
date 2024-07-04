package application.api;

import application.purchase.PurchaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface PurchaseResource {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase/{purchaseId}")
    PurchaseDto getById(@PathVariable(name = "purchaseId") String purchaseId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/purchase")
    List<PurchaseDto> getAll();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/purchase")
    PurchaseDto create(@RequestBody PurchaseDto purchase);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/purchase/{purchaseId}")
    void delete(@PathVariable(name = "purchaseId") String purchaseId);
}

package application.controllers;

import application.api.PurchaseResource;
import application.purchase.Purchase;
import application.purchase.PurchaseAssembler;
import application.purchase.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.purchase.adapter.persistence.PurchaseRepository;

import java.util.Optional;

@RestController
public class PurchaseController implements PurchaseResource {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseAssembler purchaseAssembler;

    @Autowired
    PurchaseController(PurchaseRepository purchaseRepository, PurchaseAssembler purchaseAssembler) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseAssembler = purchaseAssembler;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDto getById(String purchaseId) {
        Optional<Purchase> maybePurchase = purchaseRepository.findById(purchaseId);

        if (maybePurchase.isEmpty()) throw new PurchaseNotFound();

        return purchaseAssembler.createDto(maybePurchase.get());
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDto create(PurchaseDto purchaseDto) {
        Purchase purchase = purchaseAssembler.createModel(purchaseDto);

        Purchase createdPurchase = purchaseRepository.save(purchase);
        
        return purchaseAssembler.createDto(createdPurchase);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(String purchaseId) {
        // PASS
    }
}

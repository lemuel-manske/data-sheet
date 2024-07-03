package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import application.api.PurchaseResource;
import application.purchase.Purchase;
import application.purchase.PurchaseAssembler;
import application.purchase.PurchaseDto;

import application.purchase.adapter.persistence.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;
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
    public PurchaseDto getById(String purchaseId) {
        Optional<Purchase> maybePurchase = purchaseRepository.findById(purchaseId);

        if (maybePurchase.isEmpty()) throw new PurchaseNotFound();

        return purchaseAssembler.createDto(maybePurchase.get());
    }

    @Override
    public List<PurchaseDto> getAll() {
        List<PurchaseDto> all = new ArrayList<>();

        purchaseRepository.findAll().forEach(p -> all.add(purchaseAssembler.createDto(p)));
        return all;
    }

    @Override
    public PurchaseDto create(PurchaseDto purchaseDto) {
        Purchase purchase = purchaseAssembler.createModel(purchaseDto);

        Purchase createdPurchase = purchaseRepository.save(purchase);
        
        return purchaseAssembler.createDto(createdPurchase);
    }

    @Override
    public void delete(String purchaseId) {
        if (!purchaseRepository.existsById(purchaseId)) throw new PurchaseNotFound();

        purchaseRepository.deleteById(purchaseId);
    }
}

package application.controllers;

import application.api.PurchaseResource;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderAssembler;
import application.purchase.ProductOrderDto;
import application.purchase.Purchase;
import application.purchase.PurchaseAssembler;
import application.purchase.PurchaseDto;
import application.purchase.adapter.persistence.ProductOrderRepository;
import application.purchase.adapter.persistence.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PurchaseController implements PurchaseResource {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseAssembler purchaseAssembler;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderAssembler productOrderAssembler;

    @Autowired
    PurchaseController(PurchaseRepository purchaseRepository,
                       PurchaseAssembler purchaseAssembler,
                       ProductOrderRepository productOrderRepository,
                       ProductOrderAssembler productOrderAssembler) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseAssembler = purchaseAssembler;
        this.productOrderRepository = productOrderRepository;
        this.productOrderAssembler = productOrderAssembler;
    }

    @Override
    public PurchaseDto getPurchaseById(String purchaseId) {
        Purchase maybePurchase = purchaseRepository
                .findById(purchaseId)
                .orElseThrow(PurchaseNotFound::new);

        return purchaseAssembler.createDto(maybePurchase);
    }

    @Override
    public List<PurchaseDto> getAllPurchases() {
        List<PurchaseDto> all = new ArrayList<>();

        purchaseRepository.findAll().forEach(p -> all.add(purchaseAssembler.createDto(p)));

        return all;
    }

    @Override
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = purchaseAssembler.createModel(purchaseDto);

        Purchase createdPurchase = purchaseRepository.save(purchase);
        
        return purchaseAssembler.createDto(createdPurchase);
    }

    @Override
    public void deletePurchase(String purchaseId) {
        if (!purchaseRepository.existsById(purchaseId)) throw new PurchaseNotFound();

        purchaseRepository.deleteById(purchaseId);
    }

    @Override
    public List<ProductOrderDto> getAllPurchaseProductOrders(String purchaseId) {
        Purchase purchase = purchaseRepository
                .findById(purchaseId)
                .orElseThrow(PurchaseNotFound::new);

        return purchaseAssembler.createDto(purchase).getOrders();
    }

    @Override
    public ProductOrderDto getProductOrderById(String productOrderId) {
        ProductOrder productOrder = productOrderRepository
                .findById(productOrderId)
                .orElseThrow(ProductOrderNotFound::new);

        return productOrderAssembler.createDto(productOrder);
    }

    @Override
    public ProductOrderDto addProductOrderToPurchase(String purchaseId, ProductOrderDto productOrderDto) {
        Purchase purchase = purchaseRepository
                .findById(purchaseId)
                .orElseThrow(PurchaseNotFound::new);

        ProductOrder productOrder = productOrderAssembler.createModel(purchase, productOrderDto);

        ProductOrder createdProductOrder = productOrderRepository.save(productOrder);

        return productOrderAssembler.createDto(createdProductOrder);
    }

    @Override
    public void deleteProductOrder(String productOrderId) {
        if (!productOrderRepository.existsById(productOrderId)) throw new ProductOrderNotFound();

        productOrderRepository.deleteById(productOrderId);
    }
}

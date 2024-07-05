package application.controllers;

import application.api.ProductOrderResource;
import application.purchase.ProductOrder;
import application.purchase.ProductOrderDto;
import application.purchase.Purchase;
import application.purchase.adapter.persistence.ProductOrderRepository;
import application.purchase.adapter.persistence.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductOrderController implements ProductOrderResource {

    private final PurchaseRepository purchaseRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderAssembler productOrderAssembler;

    @Autowired
    public ProductOrderController(PurchaseRepository purchaseRepository,
                                  ProductOrderRepository productOrderRepository,
                                  ProductOrderAssembler productOrderAssembler) {
        this.purchaseRepository = purchaseRepository;
        this.productOrderRepository = productOrderRepository;
        this.productOrderAssembler = productOrderAssembler;
    }

    @Override
    public List<ProductOrderDto> getAll(String purchaseId) {
        List<ProductOrderDto> all = new ArrayList<>();

        if (!purchaseRepository.existsById(purchaseId)) throw new PurchaseNotFound();

        productOrderRepository
                .findAllByPurchaseId(purchaseId)
                .forEach(po -> all.add(productOrderAssembler.createDto(po)));

        return all;
    }

    @Override
    public ProductOrderDto getById(String productOrderId) {
        ProductOrder productOrder = productOrderRepository
                .findById(productOrderId)
                .orElseThrow(ProductOrderNotFound::new);

        return productOrderAssembler.createDto(productOrder);
    }

    @Override
    public ProductOrderDto add(String purchaseId, ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderAssembler.createModel(productOrderDto);

        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(PurchaseNotFound::new);

        productOrder.setPurchase(purchase);

        ProductOrder createdProductOrder = productOrderRepository.save(productOrder);

        return productOrderAssembler.createDto(createdProductOrder);
    }

    @Override
    public void delete(String productOrderId) {
        if (!productOrderRepository.existsById(productOrderId)) throw new ProductOrderNotFound();

        productOrderRepository.deleteById(productOrderId);
    }

    @Override
    public ProductOrderDto update(String productOrderId, ProductOrderDto productOrderDto) {
        ProductOrder productOrderToUpdate = productOrderAssembler.createModel(productOrderDto);

        ProductOrder productOrder = productOrderRepository
                .findById(productOrderId)
                .orElseThrow(ProductOrderNotFound::new);

        productOrder.setAmount(productOrderToUpdate.getAmount());
        productOrder.setProduct(productOrderToUpdate.getProduct());

        productOrderRepository.save(productOrder);

        return productOrderAssembler.createDto(productOrder);
    }

}

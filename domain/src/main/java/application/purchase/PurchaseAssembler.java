package application.purchase;

public interface PurchaseAssembler {

    PurchaseDto createDto(Purchase purchase);

    Purchase createModel(PurchaseDto purchase);
}

package application.purchase.adapter.persistence;

import application.purchase.ProductOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, String> {

    @Query(value = "select po from ProductOrder po where po.purchase.id = ?1")
    Iterable<ProductOrder> findAllByPurchaseId(String purchaseId);

    @Query(value = "select po from ProductOrder po where po.purchase.id = ?1 and po.id = ?2")
    Optional<ProductOrder> findByPurchaseAndProductOrderId(String purchaseId, String productOrderId);
}

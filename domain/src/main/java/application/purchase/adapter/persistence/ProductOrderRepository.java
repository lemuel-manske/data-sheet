package application.purchase.adapter.persistence;

import application.purchase.ProductOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, String> {

    @Query(value = "select po from ProductOrder po where po.purchase.id = ?1")
    Iterable<ProductOrder> findAllByPurchaseId(String purchaseId);

}

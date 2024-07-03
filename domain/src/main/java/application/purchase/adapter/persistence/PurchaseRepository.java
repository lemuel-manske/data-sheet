package application.purchase.adapter.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import application.purchase.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, String> {
}

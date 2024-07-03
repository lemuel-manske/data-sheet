package purchase.adapter.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import purchase.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, String> {
}

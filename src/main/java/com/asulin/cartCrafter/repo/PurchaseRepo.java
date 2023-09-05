package com.asulin.cartCrafter.repo;

import com.asulin.cartCrafter.model.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
}

package com.asulin.cartCrafter.repo;

import com.asulin.cartCrafter.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
}

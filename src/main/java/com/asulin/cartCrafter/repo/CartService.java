package com.asulin.cartCrafter.repo;

import com.asulin.cartCrafter.model.Product;
import com.asulin.cartCrafter.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PurchaseRepo purchaseRepo;

    public Purchase addPurchase(Purchase purchase){
        return purchaseRepo.save(purchase);
    }

    public String calculateNextPurchase(){
        Map<String, Integer> productsFrequencies = new HashMap<>();
        Map<String, Date> lastPurchaseDates = new HashMap<>();

        Iterable<Purchase> allPurchases = purchaseRepo.findAll();

        for (Purchase purchase : allPurchases){
            Date date = purchase.getCreatedAt();
            for (Product product: purchase.getProducts()){
                String productName = product.getName();
                int quantity = product.getQuantity();

                productsFrequencies.put(productName, productsFrequencies.getOrDefault(productName,0) + quantity);

                if(!lastPurchaseDates.containsKey(productName) || lastPurchaseDates.get(productName).before(date)){
                    lastPurchaseDates.put(productName, date);
                }
            }

        }

        List<String> nextPurchase = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : productsFrequencies.entrySet()){
            String productName = entry.getKey();
            int frequency = entry.getValue();
            Date date = lastPurchaseDates.get(productName);

            if( frequency < 3 && date != null) {
                nextPurchase.add(productName);
            }
        }

        return "For your next shopping you should buy: " + nextPurchase;
    }

    public List<String> getAllProducts(){
        List<String> allProducts = new ArrayList<>();
        Iterable<Product> products = productRepo.findAll();

        for (Product product : products){
            allProducts.add(product.toString());
        }

        return allProducts;
    }
}

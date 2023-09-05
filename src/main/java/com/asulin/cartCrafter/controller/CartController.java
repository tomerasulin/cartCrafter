package com.asulin.cartCrafter.controller;

import com.asulin.cartCrafter.model.Purchase;
import com.asulin.cartCrafter.repo.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public ResponseEntity<?> addPurchase(@RequestBody Purchase purchase){
        return new ResponseEntity<>(cartService.addPurchase(purchase), HttpStatus.OK);
    }

    @RequestMapping(value = "/nextPurchase",method = RequestMethod.GET)
    public ResponseEntity<String> getNextPurchase(){
        return new ResponseEntity<>(cartService.calculateNextPurchase(),HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllProducts(){
        return new ResponseEntity<>(cartService.getAllProducts(), HttpStatus.OK);
    }

}

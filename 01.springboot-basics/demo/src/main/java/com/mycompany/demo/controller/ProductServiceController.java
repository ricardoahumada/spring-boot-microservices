package com.mycompany.demo.controller;

import com.mycompany.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {
    private static Map<Long, Product> productRepo = new HashMap();

    static {
        Product honey = new Product();
        honey.setId(1L);
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        Product almond = new Product();
        almond.setId(2L);
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
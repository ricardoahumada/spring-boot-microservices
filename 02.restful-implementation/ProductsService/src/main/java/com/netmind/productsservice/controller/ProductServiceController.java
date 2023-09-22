package com.netmind.productsservice.controller;

import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.persistence.IProductsRepository;
import com.netmind.productsservice.persistence.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductServiceController {

    @Autowired
    private IProductsRepository repo;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @RequestMapping(path = "",method = RequestMethod.GET)
    public List<Product> getAll(){
        return repo.findAll();
    }

}
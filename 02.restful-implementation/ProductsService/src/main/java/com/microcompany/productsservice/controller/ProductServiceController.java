package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.IProductsRepository;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    ProductsService service;

    @Autowired
    private ProductsRepository repo;

   /* @RequestMapping("")
    public String info() {
        logger.info("Prouctos.info ejecutándose .......");
        return "Productos";
    }*/

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return service.getProductsByText("");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product add(@RequestBody Product newP){
        return repo.save(newP);
    }

}
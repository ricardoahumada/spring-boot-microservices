package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    ProductsRepository repo;

    @Autowired
    ProductsService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> getAll() {
        return service.getProductsByText("");
//        return repo.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product create(@RequestBody Product newP) {
        return repo.save(newP);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product geOne(@PathVariable("id") Long pid) {
        return repo.findById(pid).get();
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.PUT)
    public Product update(@PathVariable("pid") Long id, @RequestBody Product product) {
        product.setId(id);
        return repo.save(product);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("pid") Long id) {
        repo.deleteById(id);
    }

}
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

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "") String texto) {
        List<Product> prods = service.getProductsByText(texto);
        if (prods != null && !prods.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body(prods);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage(HttpStatus.NOT_FOUND.value(), "No hay productos"));
    }

    @PostMapping(value = "")
    public ResponseEntity<Product> create(@RequestBody Product newP) {
//        return repo.save(newP);
        return new ResponseEntity<>(repo.save(newP), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> geOne(@PathVariable("id") Long pid) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findById(pid).get());
    }

    @PutMapping(value = "/{pid}")
    public ResponseEntity<Product> update(@PathVariable("pid") Long id, @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.actualizar(id, product));
    }

    @DeleteMapping(value = "/{pid}")
    public ResponseEntity delete(@PathVariable("pid") Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/duplicate/{pid}")
    public ResponseEntity<Product> duplicate(@PathVariable Long pid) {
        return new ResponseEntity<>(service.duplicate(pid), HttpStatus.CREATED);
    }

}
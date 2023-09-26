package com.netmind.productsservice.controller;

import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.model.StatusMessage;
import com.netmind.productsservice.persistence.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductServiceController {

    @Autowired
    private ProductsRepository repo;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    /*@RequestMapping(path = "",method = RequestMethod.GET)
    public List<Product> getAll(){
        return repo.findAll();
    }*/

    //    @RequestMapping(path = "", method = RequestMethod.GET)
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Product>> getAll(@RequestParam(defaultValue = "") String name) {
//        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(repo.findByNameContaining(name), HttpStatus.OK);
    }

    //    @RequestMapping(path = "/{pid}", method = RequestMethod.GET)
    @GetMapping("/{pid}")
    public ResponseEntity getAProduct(@PathVariable(name = "pid") Long id) {
        Optional<Product> prod = repo.findById(id);
        if (!prod.isEmpty()) return new ResponseEntity<>(prod.get(), HttpStatus.OK);
        else return new ResponseEntity<>(new StatusMessage(1289, "No existe"), HttpStatus.NOT_FOUND);
    }

    //    @RequestMapping(path = "", method = RequestMethod.POST)
    @PostMapping(value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Product> addProduct(@RequestBody Product newP) {
        newP.setId(null);
        repo.save(newP);
        return new ResponseEntity(newP, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product aProduct) {
        aProduct.setId(id);
        repo.save(aProduct);
        return new ResponseEntity(aProduct, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
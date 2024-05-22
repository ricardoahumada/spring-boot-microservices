package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.IProductsRepository;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    //    @RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getAllProducts() {
        List<Product> prods = service.getProductsByText("");
        if (prods != null && prods.size() > 0) return ResponseEntity.status(HttpStatus.OK).body(prods);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage(HttpStatus.OK.value(), "No hay productos"));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> add(@Valid @RequestBody Product newP) {
        repo.save(newP);
        return ResponseEntity.status(HttpStatus.CREATED).body(newP);
    }

    //    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @GetMapping("/{pid}")
    public Product getAProduct(@PathVariable("pid") Long id) {
//        return repo.findById(id).get();
        return repo.findById(id).orElseThrow(() -> new ProductNotfoundException("Producto no existe: " + id));

        /*Product prod = repo.findById(id).get();
        if (prod != null) return ResponseEntity.status(HttpStatus.OK).body(prod);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage(HttpStatus.OK.value(), "No exite producto:" + id));*/
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product aProduct) {
        aProduct.setId(id);
        repo.save(aProduct);
        if (aProduct != null) return new ResponseEntity(aProduct, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_MODIFIED.value(), "No modificado"), HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
@Validated
//@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@Tag(name = "API de productos", description = "Endpoints para ccnsumir productos")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    ProductsRepository repo;

    @Autowired
    ProductsService service;


    @Operation(summary = "Para pedir todos los productos", description = "Devuelve todos los productos, pudidendo filtrar, en distintos formatos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuando hay productos a devolver."),
        @ApiResponse(responseCode = "404", description = "Cuando no hay productos a devolver.")
    })
    @GetMapping(value = "")
    public ResponseEntity<Object> getAll(
            @Parameter(name = "texto", description = "Indica un texto por el que filtrar", example = "aa, el, hola")
            @RequestParam(required = false, defaultValue = "") @Size(min = 0, max = 10) String texto
    ) {
        List<Product> prods = service.getProductsByText(texto);
        if (prods != null && !prods.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body(prods);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage(HttpStatus.NOT_FOUND.value(), "No hay productos"));
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> create(@RequestBody @Valid Product newP) {
//        return repo.save(newP);
        return new ResponseEntity<>(repo.save(newP), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> geOne(@PathVariable("id") @Min(1) Long pid) {
        return ResponseEntity.status(HttpStatus.OK).body(
                repo.findById(pid).orElseThrow(() -> new ProductNotfoundException("No existe el producto"))
        );
    }

    @PutMapping(value = "/{pid}")
    public ResponseEntity<Product> update(@PathVariable("pid") @Min(1) Long id, @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.actualizar(id, product));
    }

    @DeleteMapping(value = "/{pid}")
    public ResponseEntity delete(@PathVariable("pid") @Min(1) Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/duplicate/{pid}")
    public ResponseEntity<Product> duplicate(@PathVariable @Min(1) Long pid) {
        return new ResponseEntity<>(service.duplicate(pid), HttpStatus.CREATED);
    }

}
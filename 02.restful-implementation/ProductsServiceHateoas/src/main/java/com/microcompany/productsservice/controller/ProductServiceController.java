package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.assembler.ProductModelAssembler;
import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.resource.ProductResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/products")
// @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    ProductsRepository productsRepo;

    // TODO: uncomment
    @Autowired
    private ProductModelAssembler productModelAssembler;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        List<Product> products = productsRepo.findAll();
        System.out.println("products:" + products);
        return new ResponseEntity(productModelAssembler.toModel(products), HttpStatus.OK);
//        return new ResponseEntity(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a product by id", notes = "Returns a product as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The product was not found")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getProduct(
            @ApiParam(name = "id", value = "Product id", example = "1") @PathVariable @Min(1) Long id
    ) {
        if (!productsRepo.existsById(id)) throw new ProductNotfoundException();
        return ResponseEntity.status(HttpStatus.OK).body(productModelAssembler.toModel(productsRepo.findById(id).get()));
        /*return productsRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());*/
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProduct(@RequestBody @Valid Product newProduct) {
        newProduct.setId(null);
        productsRepo.save(newProduct);
        if (newProduct != null && newProduct.getId() > 0)
            return new ResponseEntity<>(productModelAssembler.toModel(newProduct), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "No encontrado"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@PathVariable @Min(1) Long id, @RequestBody @Valid Product aProduct) {
        aProduct.setId(id);
        productsRepo.save(aProduct);
        if (aProduct != null) return new ResponseEntity(aProduct, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_MODIFIED.value(), "No modificado"), HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProduct(@PathVariable @Min(1) Long id) {
        productsRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
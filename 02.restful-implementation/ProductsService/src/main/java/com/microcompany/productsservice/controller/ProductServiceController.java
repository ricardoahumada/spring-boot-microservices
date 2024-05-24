package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.IProductsRepository;
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

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
//@CrossOrigin(origins = "*")
@Tag(name = "API Productos", description = "Api que expone producots y servicos asociados")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    ProductsService service;

    @Autowired
    private ProductsRepository repo;

    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

   /* @RequestMapping("")
    public String info() {
        logger.info("Prouctos.info ejecutándose .......");
        return "Productos";
    }*/

    //    @RequestMapping(value = "", method = RequestMethod.GET)

    @Operation(summary = "Obtener todos los productos", description = "Devuelve todos los productos en els sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuando existen producits"),
            @ApiResponse(responseCode = "404", description = "Cuando no existen productos")
    })
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getAllProducts( @RequestParam(required = false, defaultValue = "") String text) {
        List<Product> prods = service.getProductsByText(text);
        if (prods != null && prods.size() > 0) return ResponseEntity.status(HttpStatus.OK).body(prods);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage(HttpStatus.OK.value(), "No hay productos"));
    }

    @Operation(summary = "Añadir productos", description = "Permite añadir un producto al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cuando OK"),
            @ApiResponse(responseCode = "412", description = "Cuando nOK")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> add(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo del producto", required = false)
            @Valid @RequestBody Product newP
    ) {
        repo.save(newP);
        return ResponseEntity.status(HttpStatus.CREATED).body(newP);
    }

    //    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @GetMapping("/{pid}")
    public Product getAProduct(
            @Parameter(required = true, name = "ID", description = "El identificador del producto")
            @PathVariable("pid") @Min(1) Long id
    ) {
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
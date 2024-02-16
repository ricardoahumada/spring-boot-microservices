package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.resource.ProductResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
class ProductServiceControllerTest {

   /* @Autowired
    private ProductServiceController controller;

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
        ResponseEntity<List<Product>> products = controller.getAllProducts();

        assertThat(products.getStatusCode().value())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(products.getBody())
                .isNotNull();
    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() {
        Product newProduct = new Product(null, "Nuevo producto", "123-123-1234");

        ResponseEntity<Product> response = controller.createProduct(newProduct);

        System.out.println("***** response:" + response);

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getBody().getId()).isGreaterThan(0);

    }*/
}
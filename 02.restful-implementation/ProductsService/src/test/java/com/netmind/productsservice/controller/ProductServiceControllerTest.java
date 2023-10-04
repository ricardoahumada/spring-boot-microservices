package com.netmind.productsservice.controller;

import com.netmind.productsservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

// TODO: uncomment and implement methods
@SpringBootTest
@Sql("classpath:test.sql")
class ProductServiceControllerTest {

    @Autowired
    private ProductServiceController controller;

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
        ResponseEntity<List<Product>> productos = controller.getAll("");
        System.out.println("productos:" + productos.getBody());

        assertThat(productos.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(productos.getBody()).isNotNull();
        assertThat(productos.getBody().size()).isGreaterThanOrEqualTo(5);
    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() {
        Product newP = new Product(null, "new Product", "123-122-1234");
        ResponseEntity<Product> producto = controller.addProduct(newP);
        System.out.println("producto:" + producto.getBody());

        assertThat(producto.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
        Product rP = producto.getBody();
        assertThat(rP).isNotNull();
        assertThat(rP.getId()).isGreaterThan(0);
        assertThat(rP.getName()).isEqualTo(newP.getName());
    }
}
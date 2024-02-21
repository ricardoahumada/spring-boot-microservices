package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: uncomment and implement methods
@SpringBootTest
@ActiveProfiles({"test"})
@Sql("classpath:datos_prueba.sql")
class ProductServiceControllerTest {

    @Autowired
    private ProductServiceController controller;

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
//        given
        String texto = "a";

//        when
        ResponseEntity<Object> response = controller.getAll(texto);

//        then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenProducts_whengetAllNotexisting_Then_Exception() {
//        given
        String texto = "xxxx";

//        when//        then
        assertThrows(ProductNotfoundException.class, () -> {
            controller.getAll(texto);
        });

    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() {


    }
}
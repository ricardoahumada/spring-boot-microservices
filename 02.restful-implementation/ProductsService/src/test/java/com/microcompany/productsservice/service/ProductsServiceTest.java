package com.microcompany.productsservice.service;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("classpath:datos_prueba.sql")
@ActiveProfiles({"test"})
class ProductsServiceTest {

    @Autowired
    ProductsRepository repo;

    @Autowired
    ProductsService service;
    @Test
    void actualizar() {

    }

    @Test
    void getProductsByText() {
        // given
        // conjunto de datos en bbdd
        String texto = "a";

        //when
        List<Product> products = service.getProductsByText(texto);

        //then
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    void duplicate() {
    }
}
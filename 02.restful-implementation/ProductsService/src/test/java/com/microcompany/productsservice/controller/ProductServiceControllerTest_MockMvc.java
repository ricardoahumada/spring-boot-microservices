package com.microcompany.productsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcompany.productsservice.ProductsServiceApplication;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;


// TODO: uncomment and implement methods
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
// @TestPropertySource( locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
@Sql("classpath:datos_prueba.sql")
class ProductServiceControllerTest_MockMvc {
    @Autowired
    private ProductsRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    public void givenProducts_whenGetProducts_thenStatus200() throws Exception {
        // given
        String texto = "a";

        // when - then
        mvc.perform(get("/products"))
                .andExpect(status().isOk());

    }

    @Test
    void givenProducts_whenValidCreateProduct_thenIsCreatedAndHaveId() throws Exception {

    }

}
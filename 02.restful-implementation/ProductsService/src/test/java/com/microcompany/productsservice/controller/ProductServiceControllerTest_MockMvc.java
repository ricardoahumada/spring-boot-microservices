package com.microcompany.productsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcompany.productsservice.ProductsServiceApplication;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


// TODO: uncomment and implement methods
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql("classpath:testing.sql")
// @TestPropertySource( locations = "classpath:application-integrationtest.properties")
class ProductServiceControllerTest_MockMvc {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductsRepository repository;

    @Test
    public void givenProducts_whenGetProducts_thenStatus200() throws Exception {
        Product nuevoProd = new Product(null, "Nuevo producto", "123-123-1234");
        repository.save(nuevoProd);

        mvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Nuevo producto")));

    }

    @Test
    void givenProducts_whenValidCreateProduct_thenIsCreatedAndHaveId() throws Exception {
        Product newProduct = new Product(null, "Nuevo producto", "123-123-1234");

        mvc.perform(post("/products")
                        .content(JsonUtil.asJsonString(newProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

}
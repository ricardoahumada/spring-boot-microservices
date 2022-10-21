package com.netmind.productsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netmind.productsservice.ProductsServiceApplication;
import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProductsServiceApplication.class)
@AutoConfigureMockMvc
//@TestPropertySource( locations = "classpath:application-integrationtest.properties")
class ProductServiceControllerTest_MockMvc {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductsRepository repository;

    @Test
    public void givenProducts_whenGetProducts_thenStatus200() throws Exception {
        Product nuevoProd = new Product(null, "Nuevo Prod", "123-123-1234");
        repository.save(nuevoProd);

        mvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Nuevo Prod")));

    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() throws Exception {
        Product newProduct = new Product(null, "Nuevo producto", "123-123-1234");

        mvc.perform(post("/products")
                        .content(asJsonString(newProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
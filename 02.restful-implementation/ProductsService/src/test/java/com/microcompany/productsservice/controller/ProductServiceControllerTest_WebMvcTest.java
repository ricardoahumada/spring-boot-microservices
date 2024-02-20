package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import com.microcompany.productsservice.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

// TODO: uncomment and implement methods
@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductServiceController.class)
public class ProductServiceControllerTest_WebMvcTest {

    @BeforeEach
    public void setUp() {

        List<Product> products = Arrays.asList(
                new Product(1L, "Candy", "")
        );

        Mockito.when(service.getProductsByText("Fake"))
                .thenReturn(products);

        Mockito.when(repository.findByNameContaining("Fake"))
                .thenReturn(products);

        Mockito.when(repository.findAll())
                .thenReturn(products);

        Mockito.when(repository.save(Mockito.any(Product.class)))
                .thenAnswer(elem -> {
                    Product ap = (Product) elem.getArguments()[0];
                    ap.setId(100L);
                    return ap;
                });
    }


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsService service;

    @MockBean
    private ProductsRepository repository;


    @Test
    public void givenProducts_whenGetProducts_thenStatus200() throws Exception {
        // given
        String texto = "Fake";

        // when - then
        mvc.perform(get("/products?texto=" + texto).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[*].name", hasItem("Candy")))
        ;

    }

    @Test
    void givenProducts_whenValidCreateProduct_thenIsCreatedAndHaveId() throws Exception {
        // given
        Product newPoduct = new Product(null, "Escoba", "123-123-1234");

        // when - then
        mvc.perform(post("/products")
                        .content(JsonUtil.asJsonString(newPoduct))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", is(greaterThan(0))))
        ;

    }

}
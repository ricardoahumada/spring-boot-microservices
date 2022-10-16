package com.netmind.productsservice.controller;

import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.persistence.ProductsRepository;
import com.netmind.productsservice.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplIntegrationTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
        @Bean
        public ProductsService productsService() {
            return new ProductsService();
        }
    }


    @BeforeEach
    public void setUp() {
        Product aProd = new Product();
        aProd.setId(1L);
        aProd.setName("Fake product");
        List<Product> products=new ArrayList();
        products.add(aProd);
        Mockito.when(productsRepository.findByNameContaining("Fake"))
                .thenReturn(products);
    }

    @Autowired
    private ProductsService productsService;
    @MockBean
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsRepository productRepo;

    @Test
    public void whenValidText_thenProductsShouldBeFound() {
        String text = "Fake";
        List<Product> found = productsService.getProductsByText(text);
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getName()).contains("Fake");

    }

}

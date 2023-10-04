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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ProductServiceIntegrationTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
        @Bean
        public ProductsService productsService() {
            return new ProductsService();
        }

        @Bean
        public ProductServiceController productsServiceController() {
            return new ProductServiceController();
        }
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Fake product", "")
        );

        Mockito.when(productsRepository.findByNameContaining("Fake"))
                .thenReturn(products);

        Mockito.when(productsRepository.findAll())
                .thenReturn(products);
    }

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductServiceController controller;
    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void whenValidText_thenProductsShouldBeFound() {
        String text = "Fake";
        List<Product> found = productsService.getProductsByText(text);
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getName()).contains("Fake");

    }

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
        ResponseEntity<List<Product>> products = controller.getAllProducts();

        assertThat(products.getStatusCode().value())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(products.getBody()).isNotNull();
        assertThat(products.getBody().size()).isGreaterThanOrEqualTo(1);

    }

}
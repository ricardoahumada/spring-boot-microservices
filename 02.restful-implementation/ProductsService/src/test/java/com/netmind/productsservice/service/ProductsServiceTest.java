package com.netmind.productsservice.service;

import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ProductsServiceTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
        @Bean
        public ProductsService productsService() {
            return new ProductsService();
        }

    }

    @BeforeEach
    public void setUp() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Fake product", "")
        );

        Mockito.when(productsRepository.findByNameContaining("a"))
                .thenReturn(products);
    }

    @Autowired
    ProductsService productsService;

    @MockBean
    private ProductsRepository productsRepository;

    @Test
    void givenProductsWhenSearchByTextThenIsNotNull() {
        List<Product> products = productsService.getProductsByText("a");
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }
}
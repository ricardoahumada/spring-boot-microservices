package com.microcompany.productsservice.service;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@DataJpaTest()
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
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);

        List<Product> products = Arrays.asList(
                new Product(1L, "Fake product", "")
        );

        Mockito.when(productsRepository.findByNameContaining("a"))
                .thenReturn(products);
    }


    @MockBean
    private EntityManager entityManager;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @MockBean
    private ProductsRepository productsRepository;

    @Autowired
    ProductsService productsService;


    @Test
    void givenProductsWhenSearchByTextThenIsNotNull() {
        List<Product> products = productsService.getProductsByText("a");
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }
}
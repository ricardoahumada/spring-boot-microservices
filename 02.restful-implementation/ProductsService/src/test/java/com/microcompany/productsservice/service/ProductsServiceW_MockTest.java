package com.microcompany.productsservice.service;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.IProductsRepository;
import com.microcompany.productsservice.persistence.JPAProductsRepository;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(ProductsService.class)
class ProductsServiceW_MockTest {

    @TestConfiguration
    static class RepoConfig {
        @Bean
        public IProductsRepository getProductsRepository() {
            return new JPAProductsRepository();
        }
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = List.of(
                new Product(null, "a1", "123-123-1234"),
                new Product(null, "a2", "123-123-1234"),
                new Product(null, "a3", "123-123-1234"),
                new Product(null, "a4", "123-123-1234")
        );

        Mockito.when(repo.findByNameContaining("a")).thenReturn(products);
    }

    @MockBean
    private EntityManagerFactory emf;

    @MockBean
    private EntityManager em;

    @MockBean
    IProductsRepository repo;

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
        assertThat(products.size()).isEqualTo(4);
    }

    @Test
    void duplicate() {
    }
}
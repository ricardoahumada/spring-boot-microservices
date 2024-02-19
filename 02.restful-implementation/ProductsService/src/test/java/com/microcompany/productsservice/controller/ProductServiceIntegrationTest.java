package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.IProductsRepository;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@DataJpaTest()
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
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);

        List<Product> products = Arrays.asList(
                new Product(1L, "Fake product", "")
        );

        Mockito.when(productsRepository.findByNameContaining("Fake"))
                .thenReturn(products);

        Mockito.when(productsRepository.findAll())
                .thenReturn(products);

        Mockito.when(productsRepository.save(Mockito.any(Product.class)))
                .thenAnswer(elem -> {
                    Product ap = (Product) elem.getArguments()[0];
                    ap.setId(100L);
                    return ap;
                });

        Mockito.when(productsService.getProductsByText("Fake"))
                .thenReturn(products);

        Mockito.when(productsService.getProductsByText(""))
                .thenReturn(products);
    }

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @MockBean
    private ProductsRepository productsRepository;

    @MockBean
    private IProductsRepository repo;


    @Autowired
    private ProductsService productsService;


    @Autowired
    private ProductServiceController controller;


    @Test
    public void whenValidText_thenProductsShouldBeFound() {
        String text = "Fake";
        List<Product> found = productsService.getProductsByText(text);
        System.out.println("****found:" + found);
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getName()).contains("Fake");

    }

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
        String texto = "";
        ResponseEntity<Object> products = controller.getAll(texto);

        assertThat(products.getStatusCode().value())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(products.getBody()).isNotNull();

    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() {
        Product newProduct = new Product(null, "Nuevo producto", "123-123-1234");

        ResponseEntity<Product> response = controller.create(newProduct);
        System.out.println("***** response:" + response);

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getBody().getId()).isGreaterThan(0);

    }

}
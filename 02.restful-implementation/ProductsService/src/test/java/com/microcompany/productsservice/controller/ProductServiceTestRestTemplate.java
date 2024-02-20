package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: uncomment and implement methods
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@Sql("classpath:datos_prueba.sql")
public class ProductServiceTestRestTemplate {
    // @Value(value = "${local.server.port}")
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenUrl_whenGetProducts_thenAStringExists() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/products", String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Magazine");
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    public void givenUrl_whenGetProducts_thenAProductExists() throws Exception {
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/products", Product[].class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(response.getBody())
                .extracting(Product::getName)
                .containsExactlyInAnyOrder("Magazine");
    }

    @Test
    public void givenAProduct_whenPostWithHeader_thenSuccess() throws URISyntaxException {
        Product product = new Product(null, "New book", "123-123-1234");

        HttpHeaders headers = new HttpHeaders();
        headers.add("ACCEPT", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Product> request = new HttpEntity<>(product, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/api/products", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getBody()).contains("New book");

    }

}

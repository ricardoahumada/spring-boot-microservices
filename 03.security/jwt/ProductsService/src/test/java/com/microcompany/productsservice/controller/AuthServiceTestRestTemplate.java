package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.*;
import com.microcompany.productsservice.model.*;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.persistence.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthServiceTestRestTemplate {

    Logger logger = LoggerFactory.getLogger(AuthServiceTestRestTemplate.class);

    String accessToken = null;

    @MockBean
    private ProductsRepository productsRepository;

    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
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
    }

    @Test
    @Order(1)
    void given_existing_user_when_login_success() throws Exception {
        // given
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String email = "r@r.com";
        String password = "pa55wrd";
        String enc_password = passwordEncoder.encode(password);

        User aUser = new User(null, email, enc_password, ERole.USER);
        userRepository.save(aUser);
        logger.info("Saved user:" + aUser);

        // when
        AuthRequest authRequest = new AuthRequest(email, password);

        //then
        final String baseUrl = "http://localhost:" + port + "/auth/login";
        logger.info("baseUrl:" + baseUrl);
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("CONTENT-TYPE", "application/json");
        headers.set("ACCEPT", "application/json");
        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest, headers);

        ResponseEntity<AuthResponse> response = this.restTemplate.postForEntity(uri, request, AuthResponse.class);

        System.out.println(response);
        logger.info("Response: " + response);

        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        AuthResponse authResponse = response.getBody();
        assertThat(authResponse.getAccessToken()).isNotBlank().isNotEmpty().isNotNull();

        accessToken = authResponse.getAccessToken();
    }

    @Test
    @Order(2)
    void given_accesstoken_when_getproducts_then_success() throws Exception {
        // given: existing token
        // given_existing_user_when_login_success(); // must  have executed previously in test sequence

        // when
        final String baseUrl = "http://localhost:" + port + "/products";
        logger.info("baseUrl:" + baseUrl);
        URI uri = new URI(baseUrl);


        HttpHeaders authzHeaders = new HttpHeaders();
        authzHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        authzHeaders.add("Authorization", "Bearer " + accessToken);
        authzHeaders.add("Content-Type", "application/json");


        ResponseEntity<Product[]> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity<>(null, authzHeaders),
                Product[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        Product[] responseBody = response.getBody();
        assertThat(responseBody)
                .extracting(Product::getName)
                .contains("Fake product");
    }

}
package com.microcompany.microservices.ordersservice.controller;

import com.microcompany.microservices.ordersservice.model.Order;
import io.fabric8.kubernetes.client.server.mock.EnableKubernetesMockClient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.main.cloud-platform=KUBERNETES",
                "spring.cloud.bootstrap.enabled=true"})
/*@EnableKubernetesMockClient(crud = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)*/
class OrderServiceControllerTest {

    @DynamicPropertySource
    static void registerMongoProperties(DynamicPropertyRegistry registry) {
    }

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void findAllOrders() {
        Order[] orders = restTemplate.getForObject("/", Order[].class);
        assertEquals(2, orders.length);
    }

    @Test
    void createOrder() {
    }
}
package com.microcompany.productsservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);


    @RequestMapping("")
    public String info() {
        logger.info("Prouctos.info ejecutándose .......");
        return "Productos";
    }

}
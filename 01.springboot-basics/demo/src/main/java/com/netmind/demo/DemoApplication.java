package com.netmind.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ServletComponentScan // for reading @webfilter
public class DemoApplication {
   private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

   @Value("${spring.application.name}")
   private String name;
   
   public static void main(String[] args) {
      logger.info("this is a info message");
      logger.warn("this is a warn message");
      logger.error("this is a error message");

      SpringApplication.run(DemoApplication.class, args);
   }

   @RequestMapping(value = "/name")
   public String name() {
      return name;
   }
}
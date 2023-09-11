package com.netmind.microservices.limitsservice.controller;

import com.netmind.microservices.limitsservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netmind.microservices.limitsservice.model.LimitConfiguration;

@RestController
@RequestMapping("/limits")
public class LimitsConfigurationController {
    @Autowired
    private Configuration configuration;

    @GetMapping("")
    public LimitConfiguration retriveLimitsFromConfigurations() {
        //getting values from the properties file
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}  
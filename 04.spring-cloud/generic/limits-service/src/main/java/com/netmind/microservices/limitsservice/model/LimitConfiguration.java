package com.netmind.microservices.limitsservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
public class LimitConfiguration {
    private int maximum;
    private int minimum;
}
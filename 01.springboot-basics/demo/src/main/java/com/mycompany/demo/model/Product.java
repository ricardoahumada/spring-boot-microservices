package com.mysbapp.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
}

/*
public record Product(Long id, String name) {
}
*/

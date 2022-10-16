package com.netmind.productsservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Min(0)
    private Long id;

    @Column
    @Getter
    @Setter
    @NotBlank
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
}

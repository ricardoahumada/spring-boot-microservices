package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column
    @NonNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Column
    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")
    private String serial;
}
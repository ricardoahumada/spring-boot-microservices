package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.microcompany.productsservice.constraints.SerialNumber;
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
    @Size(min=3, max = 20, message = "debe ser un nombre con 3-20 chars")
    @NonNull
    @NotBlank(message = "El nombre no puede ser vacio")
    private String name;

    @Column
//    @Pattern(regexp = "[1-9]*3-[1-9]*3-[1-9]*4")
    @SerialNumber(message = "{serial.format}")
    private String serial;
}
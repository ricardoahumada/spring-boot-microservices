package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.microcompany.productsservice.constraints.SerialNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(name = "Producto", description = "Modelo del producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Id de producto", example = "234", required = true)
    private Long id;

    @Column
    @Size(min=3, max = 20, message = "debe ser un nombre con 3-20 chars")
    @NonNull
    @NotBlank(message = "El nombre no puede ser vacio")
    @Schema(name = "Nombre de producto", example = "Periódico local", required = true)
    private String name;

    @Column
//    @Pattern(regexp = "[1-9]*3-[1-9]*3-[1-9]*4")
    @SerialNumber(message = "{serial.format}")
    @Schema(name = "Num serie de producto", example = "234-567-1890", required = true)
    private String serial;
}
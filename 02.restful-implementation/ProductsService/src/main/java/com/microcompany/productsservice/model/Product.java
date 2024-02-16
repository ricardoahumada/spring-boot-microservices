package com.microcompany.productsservice.model;

import com.microcompany.productsservice.constraints.ProductName;
import com.microcompany.productsservice.constraints.SerialNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement
@Schema(name = "Modelo de producto", description = "Representa un producto que podemos comprar")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Porduct id", description = "Identificador del producto basado en números enteros positovos", example = "234")
    private Long id;

    @Column
//    @NonNull
    @NotBlank
//    @Size(min = 3, max = 20)
    @ProductName(message = "{product.name}")
    @Schema(name = "El nombre del producto", description = "El nombre comercial del producto en caracters alfanuméricos", example = "Harry Potter 5")
    private String name;

    @Column
//    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")
    @SerialNumber(message = "{serial.format}")
    @Schema(name = "Número de serie del producto", description = "Usado en almacen y con fommato ddd-ddd-dddd", example = "123-123-1234")
    private String serial;
}
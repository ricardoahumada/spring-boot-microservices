package com.netmind.productsservice.model;

import com.netmind.productsservice.constraints.SerialNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "products")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@ApiModel(description = "Modelo producto")
@XmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    @ApiModelProperty(notes = "Product ID", example = "1", required = false)
    private Long id;

    @Column
    @NotBlank(message = "Debe tener valor")
    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "Product name", example = "Product 1", required = true)
    private String name;

    @Column
//    @NotNull @NotBlank(message = "Debe tener valor con formato ddd-ddd-dddd")
    @SerialNumber(message="{serial.format}")
    @ApiModelProperty(notes = "Product serial number", example = "111-222-3333", required = true)
    private String serial;
}

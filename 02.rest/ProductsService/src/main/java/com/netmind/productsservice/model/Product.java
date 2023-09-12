package com.netmind.productsservice.model;

import com.netmind.productsservice.constraints.SerialNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "A Product model")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    @Schema(name = "Product ID", example = "1", required = true)
    private Long id;

    @Column
    @NotBlank
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Column
    @NotBlank(message = "{serial.not.blank}")
    @NotNull
    @SerialNumber(message = "{serial.format}")
    private String serial;
}

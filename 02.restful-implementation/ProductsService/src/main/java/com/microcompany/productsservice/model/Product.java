package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.microcompany.productsservice.constraints.ProductName;
import com.microcompany.productsservice.constraints.SerialNumber;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column
//    @NonNull
    @NotBlank
//    @Size(min = 3, max = 20)
    @ProductName(message = "{product.name}")
    private String name;

    @Column
//    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")
    @SerialNumber(message = "{serial.format}")
    private String serial;
}
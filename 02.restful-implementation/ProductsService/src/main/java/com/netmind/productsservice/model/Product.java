package com.netmind.productsservice.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JacksonXmlRootElement
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    private Long id;

    @Column
    @NotBlank(message = "El nombre debe tener valor")
    @NonNull
    @Size(min = 3, max = 50)
    private String name;

    /*@Column
    private String serial;*/
}

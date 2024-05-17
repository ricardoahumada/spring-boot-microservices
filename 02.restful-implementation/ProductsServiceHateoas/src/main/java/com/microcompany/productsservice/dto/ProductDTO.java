package com.microcompany.productsservice.dto;

import com.microcompany.productsservice.constraints.SerialNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Modelo producto")
@XmlRootElement
public class ProductDTO {
    @Min(0)
    @ApiModelProperty(notes = "Product ID", example = "1", required = false)
    private Long id;

    @Column
    @NotBlank(message = "Debe tener valor")
    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "Product name", example = "Product 1", required = true)
    private String nombre;

    @Column
//    @NotNull @NotBlank(message = "Debe tener valor con formato ddd-ddd-dddd")
    @SerialNumber(message = "{serial.format}")
    @ApiModelProperty(notes = "Product serial number", example = "111-222-3333", required = true)
    private String numSerie;
}
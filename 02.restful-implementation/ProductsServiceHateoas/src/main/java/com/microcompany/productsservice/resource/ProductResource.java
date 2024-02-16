package com.microcompany.productsservice.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.microcompany.productsservice.constraints.SerialNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "Product", description = "Modelo producto")
@XmlRootElement
public class ProductResource extends RepresentationModel<ProductResource> {
    @Min(0)
    @ApiModelProperty(name = "Product ID", example = "1", required = false)
    private Long id;

    @NotBlank(message = "Debe tener valor")
    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(name = "Product name", example = "Product 1", required = true)
    private String nombre;

    @NotNull
    @NotBlank(message = "Debe tener valor con formato ddd-ddd-dddd")
    @SerialNumber(message = "{serial.format}")
    @ApiModelProperty(name = "Product serial number", example = "111-222-3333", required = true)
    private String numSerie;
}

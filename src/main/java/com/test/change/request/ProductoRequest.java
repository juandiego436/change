package com.test.change.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductoRequest {
    
    @ApiModelProperty(position = 1, required = true, name = "nombre", dataType = "String", example = "Smartphone")
    @NotEmpty(message = "Ingrese su nombre")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String nombre;
    
    @ApiModelProperty(position = 2, required = true, name = "precio", dataType = "String", example = "1000.50")
    @NotNull(message = "Ingrese su precio")
    @Digits(integer=8, fraction=2)
    private Double precio;
    
    @ApiModelProperty(position = 3, required = true, name = "stock", dataType = "String", example = "100")
    @NotNull(message = "Ingrese su stock")
    @PositiveOrZero(message = "Valores invalido")
    private Integer stock;
}

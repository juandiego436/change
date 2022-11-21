
package com.test.change.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonaRequest {
    @ApiModelProperty(position = 0,dataType = "String", required = true, value = "JD",example = "Juan Diego", notes = "Es requerido el dato")
    private String nombre;
    @ApiModelProperty(position = 1,dataType = "String", required = true, example = "juandiego@gmail.com")
    private String email;
    @ApiModelProperty(position = 2,dataType = "String", required = true, example = "1992-01-12")
    private Date fechaNacimiento;
    @ApiModelProperty(position = 3,dataType = "String", required = true, example = "12345678")
    private String password;
    @ApiModelProperty(position = 4,dataType = "Object", required = true)
    private RolRequest rol;
}

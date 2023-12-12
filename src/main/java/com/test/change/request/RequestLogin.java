package com.test.change.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestLogin {
    
    @ApiModelProperty(position = 1, required = true, name = "email", dataType = "String", example = "email@dominio.com")
    @NotEmpty(message = "Ingrese su correo electronico")
    @Email(message = "correo invalido")
    private String email;
    
    @ApiModelProperty(position = 2, required = true, name = "password", dataType = "String", example = "LgoptimusG3#")
    @NotEmpty(message = "Ingrese su password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.,#])([A-Za-z\\d$@$!%*?&.,#&]|[^ ]){8,15}$")
    private String password;
}

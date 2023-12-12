
package com.test.change.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonaRequest {
    
    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "nombre", example = "Luis")
    @NotEmpty(message = "Ingrese su nombre")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Nombre invalido")
    private String nombre;
    
    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "apellido", example = "Castro")
    @NotEmpty(message = "Ingrese su apellido")
    @Pattern(regexp ="^[a-zA-Z ]+$" , message = "Apellido invalido")
    private String apellido;
    
    @ApiModelProperty(position = 4, required = true, name = "edad", dataType = "String", example = "18")
    @NotNull(message = "Ingrese su edad")
    @PositiveOrZero(message = "Solo valores positivos")
    private Integer edad;
    
    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "dni", example = "07895623")
    @NotEmpty(message = "Ingrese su dni")
    @Size(min = 8, max = 8, message = "debe ingresar un número de documento Valida")
    @Pattern(regexp = "\\d{8}", message = "DNI Invalido")
    private String dni;
    
    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "rolNombre", example = "ROLE_ADMIN")
    @NotEmpty(message = "Ingrese su rolNombre")
    @Pattern(regexp = "^[ROLE_USER|ROLE_ADMIN]+$", message = "Rol no aceptado")
    private String rolNombre;
    
    @ApiModelProperty(position = 0,dataType = "String", required = true, name = "nickname", example = "admin001")
    @NotEmpty(message = "Ingrese su nickname")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Nickname no aceptado")
    private String nickname;
    
    @ApiModelProperty(position = 4, required = true, name = "correo", dataType = "String", example = "email@dominio.com")
    @NotEmpty(message = "Ingrese su correo electronico")
    @Email(message = "correo invalido")
    private String correo;
    
    @ApiModelProperty(position = 4, required = true, name = "password", dataType = "String", example = "Lgoptimusg3#")
    @NotEmpty(message = "Ingrese su password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&.,#])([A-Za-z\\d$@$!%*?&.,#&]|[^ ]){8,15}$", message = "Password invalido")
    private String password;
}

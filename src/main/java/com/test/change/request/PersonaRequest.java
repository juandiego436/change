
package com.test.change.request;

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
    private String nombre;
    private String email;
    private Date fechaNacimiento;
    private String password;
    private RolRequest rol;
}

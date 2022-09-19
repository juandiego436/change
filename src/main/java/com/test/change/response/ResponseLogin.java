package com.test.change.response;

import com.test.change.entity.Persona;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLogin {
    private String token;
    private Persona persona;
    private Collection<? extends GrantedAuthority> authorities;
}

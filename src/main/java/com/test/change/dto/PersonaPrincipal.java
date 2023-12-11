package com.test.change.dto;

import com.test.change.entity.Persona;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PersonaPrincipal implements UserDetails {

    private String nombre;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public PersonaPrincipal(String nombre, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static PersonaPrincipal build(Persona persona){
        List<GrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority(persona.getRoles().getRolNombre()));
        return new PersonaPrincipal(persona.getNombre(), persona.getRoles().getNickname(), persona.getRoles().getPassword(), authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}

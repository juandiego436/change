package com.test.change.service.impl;

import com.test.change.dto.PersonaPrincipal;
import com.test.change.entity.Persona;
import com.test.change.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonaService personaService;
            
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = personaService.persona(username).get();
        return PersonaPrincipal.build(persona);
    }
    
}

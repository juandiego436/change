package com.test.change.service.impl;

import com.test.change.entity.Persona;
import com.test.change.entity.Rol;
import com.test.change.repository.PersonaRepository;
import com.test.change.repository.RolRepository;
import com.test.change.request.PersonaRequest;
import com.test.change.response.Response;
import com.test.change.response.ResponseLogin;
import com.test.change.security.jwt.JwtProvider;
import com.test.change.service.PersonaService;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    
    private static final Logger LOG = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    RolRepository rolrepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public Response crear(PersonaRequest request) {
        try {
            if(request.getNombre() == null && request.getEmail().isEmpty() && request.getPassword().isEmpty()){
                return new Response(null,"Error en los campos", HttpStatus.BAD_REQUEST);
            }
            Persona persona = new Persona();
            Rol rol = new Rol();
            Set<Rol> roles = new HashSet<>();
            persona.setNombre(request.getNombre());
            persona.setEmail(request.getEmail());
            persona.setPassword(passwordEncoder.encode(request.getPassword()));
            if(rolrepository.findByRolNombre(request.getRol().getRolNombre()).isPresent()){
                rol = rolrepository.findByRolNombre(request.getRol().getRolNombre()).get();
            }else{
                rol.setRolNombre(request.getRol().getRolNombre());
                rol = rolrepository.save(rol);
            }
            roles.add(rol);
            persona.setRoles(roles);
            var result = personaRepository.save(persona);
            return new Response(null, "OK", HttpStatus.CREATED);
        } catch (Exception ex) {
            LOG.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<Persona> persona(String email) {
        return personaRepository.findByEmail(email);
    }

    @Override
    public Response Login(String email, String password) {
        try{
            ResponseLogin login = new ResponseLogin();
            if(email.isEmpty() && password.isEmpty()){
                return new Response(null,"Error en los campos", HttpStatus.BAD_REQUEST);
            }
            
            if(!personaRepository.findByEmail(email).isPresent()){
                return new Response(null,"Error no existe persona", HttpStatus.NO_CONTENT);
            }
            var persona = personaRepository.findByEmail(email).get();
            if(persona.getDeletedAt() != null){
                return new Response(null,"Error no existe persona", HttpStatus.NO_CONTENT);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);
            UserDetails userDeatails = (UserDetails) authentication.getPrincipal();
            login.setPersona(persona);
            login.setToken(token);
            login.setAuthorities(userDeatails.getAuthorities());
            return new Response(login, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response Actualizar(Long id, PersonaRequest request) {
        try{
            if(request.getNombre() == null && request.getEmail().isEmpty() && request.getPassword().isEmpty()){
                return new Response(null,"Error en los campos", HttpStatus.BAD_REQUEST);
            }

            if(!personaRepository.existsById(id)){
                 return new Response(null,"Error no existe Persona", HttpStatus.NO_CONTENT);
            }
            var persona = personaRepository.findById(id).get();
            Set<Rol> roles = persona.getRoles();
            persona.setNombre(request.getNombre());
            persona.setEmail(request.getEmail());
            Rol rol = new Rol();
            rol.setRolNombre(request.getRol().getRolNombre());
            roles.add(rol);
            persona.setRoles(roles);
            var result = personaRepository.save(persona);
            if(result == null){
                return new Response(null,"Error no se pudo Actualizar", HttpStatus.NOT_MODIFIED);
            }
            return new Response(result, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response eliminar(Long id) {
        try{
            if(!personaRepository.existsById(id)){
                return new Response(null,"Error no existe Persona", HttpStatus.NO_CONTENT);
            }
            var persona = personaRepository.findById(id).get();
            persona.setDeletedAt(new Date());
            var result = personaRepository.save(persona);
            if(result == null){
                return new Response(null,"Error no se pudo Eliminar", HttpStatus.NOT_IMPLEMENTED);
            }
            return new Response(null, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response lista() {
        try{
            var personas = personaRepository.listado();
            if(!personas.iterator().hasNext()){
                return new Response(null,"Error no existe personas", HttpStatus.NO_CONTENT);
            }
            
            return new Response(personas, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

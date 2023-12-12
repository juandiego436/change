package com.test.change.service.impl;

import com.test.change.entity.Persona;
import com.test.change.entity.Rol;
import com.test.change.repository.PersonaRepository;
import com.test.change.repository.RolRepository;
import com.test.change.request.PersonaRequest;
import com.test.change.request.PersonaUpdateRequest;
import com.test.change.request.RequestLogin;
import com.test.change.response.Response;
import com.test.change.response.ResponseLogin;
import com.test.change.security.jwt.JwtProvider;
import com.test.change.service.PersonaService;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {


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
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<PersonaRequest>> constraintViolations = validator.validate(request);
            if (constraintViolations.iterator().hasNext()) {
                return new Response(null, constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
            }
            Persona persona = new Persona();
            Rol rol = new Rol();
            persona.setNombre(request.getNombre());
            persona.setApellido(request.getApellido());
            persona.setDni(request.getDni());
            persona.setEdad(request.getEdad());
            rol.setCorreo(request.getCorreo());
            rol.setRolNombre(request.getRolNombre());
            rol.setNickname(request.getNickname());
            rol.setPassword(passwordEncoder.encode(request.getPassword()));
            rol.setUsuario(persona);
            persona.setRoles(rol);
            var result = personaRepository.save(persona);
            return new Response(null, "OK", HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<Persona> persona(String email) {
        return personaRepository.findByEmail(email);
    }

    @Override
    public Response Login(RequestLogin request) {
        try {
            ResponseLogin login = new ResponseLogin();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<RequestLogin>> constraintViolations = validator.validate(request);
            if (constraintViolations.iterator().hasNext()) {
                return new Response(null, constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
            }

            if (!personaRepository.findByEmail(request.getEmail()).isPresent()) {
                return new Response(null, "Error no existe persona", HttpStatus.NO_CONTENT);
            }
            var persona = personaRepository.findByEmail(request.getEmail()).get();
            if (persona.getDeletedAt() != null) {
                return new Response(null, "Error no existe persona", HttpStatus.NO_CONTENT);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);
            UserDetails userDeatails = (UserDetails) authentication.getPrincipal();
            login.setPersona(persona);
            login.setToken(token);
            login.setAuthorities(userDeatails.getAuthorities());
            return new Response(login, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response Actualizar(Long id, PersonaUpdateRequest request) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<PersonaUpdateRequest>> constraintViolations = validator.validate(request);
            if (constraintViolations.iterator().hasNext()) {
                return new Response(null, constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
            }
            if (!personaRepository.existsById(id)) {
                return new Response(null, "Error no existe Persona", HttpStatus.NO_CONTENT);
            }
            var persona = personaRepository.findById(id).get();
            Rol rol = new Rol();
            persona.setNombre(request.getNombre());
            persona.setApellido(request.getApellido());
            persona.setDni(request.getDni());
            persona.setEdad(request.getEdad());
            rol.setCorreo(request.getCorreo());
            rol.setNickname(request.getNickname());
            persona.setRoles(rol);
            var result = personaRepository.save(persona);
            if (result == null) {
                return new Response(null, "Error no se pudo Actualizar", HttpStatus.NOT_MODIFIED);
            }
            return new Response(result, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response eliminar(Long id) {
        try {
            if (!personaRepository.existsById(id)) {
                return new Response(null, "Error no existe Persona", HttpStatus.NO_CONTENT);
            }
            var persona = personaRepository.findById(id).get();
            persona.setDeletedAt(new Date());
            var result = personaRepository.save(persona);
            if (result == null) {
                return new Response(null, "Error no se pudo Eliminar", HttpStatus.NOT_IMPLEMENTED);
            }
            return new Response(null, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response lista() {
        try {
            var personas = personaRepository.listado();
            if (!personas.iterator().hasNext()) {
                return new Response(null, "Error no existe personas", HttpStatus.NO_CONTENT);
            }

            return new Response(personas, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response obtener(Long id) {
        try {

            if (!personaRepository.existsById(id)) {
                return new Response(null, "Error no existe personas", HttpStatus.NO_CONTENT);
            }
            var personas = personaRepository.findById(id).get();
            return new Response(personas, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

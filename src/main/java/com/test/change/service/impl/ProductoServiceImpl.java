package com.test.change.service.impl;

import com.test.change.entity.Producto;
import com.test.change.repository.PersonaRepository;
import com.test.change.repository.RepositoryProducto;
import com.test.change.request.ProductoRequest;
import com.test.change.response.Response;
import com.test.change.service.ProductoService;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    RepositoryProducto repositoryProducto;
    
    @Override
    public Response crear(ProductoRequest request) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<ProductoRequest>> constraintViolations = validator.validate(request);
            if (constraintViolations.iterator().hasNext()) {
                return new Response(null, constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
            }
            var auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDeatails = (UserDetails) auth.getPrincipal();
            var persona = personaRepository.findByEmail(userDeatails.getUsername()).get();
            Producto prod = new Producto();
            prod.setNombre(request.getNombre());
            prod.setStock(request.getStock());
            prod.setPrecio(request.getPrecio());
            prod.setUsuario(persona);
            var result = repositoryProducto.save(prod);
            return new Response(null, "OK", HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response Actualizar(Long id, ProductoRequest request) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<ProductoRequest>> constraintViolations = validator.validate(request);
            if (constraintViolations.iterator().hasNext()) {
                return new Response(null, constraintViolations.iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
            }
            var auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDeatails = (UserDetails) auth.getPrincipal();
            var persona = personaRepository.findByEmail(userDeatails.getUsername()).get();
            if(!repositoryProducto.findById(id).isPresent()){
                return new Response(null, "No existe producto", HttpStatus.NO_CONTENT);
            }
            Producto prod = repositoryProducto.findById(id).get();
            prod.setNombre(request.getNombre());
            prod.setStock(request.getStock());
            prod.setPrecio(request.getPrecio());
            prod.setUsuario(persona);
            var result = repositoryProducto.save(prod);
            return new Response(null, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response eliminar(Long id) {
        try {
            if(!repositoryProducto.findById(id).isPresent()){
                return new Response(null, "No existe producto", HttpStatus.NO_CONTENT);
            }
            repositoryProducto.deleteById(id);
            return new Response(null, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response lista() {
        try {
            var result = repositoryProducto.findAll();
            return new Response(result, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response obtener(Long id) {
        try {
            if(!repositoryProducto.findById(id).isPresent()){
                return new Response(null, "No existe producto", HttpStatus.NO_CONTENT);
            }
            Producto prod = repositoryProducto.findById(id).get();
            return new Response(prod, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en Crear Persona ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

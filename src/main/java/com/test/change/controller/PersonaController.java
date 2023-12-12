package com.test.change.controller;

import com.test.change.dto.PersonaPrincipal;
import com.test.change.request.PersonaRequest;
import com.test.change.request.PersonaUpdateRequest;
import com.test.change.request.RequestLogin;
import com.test.change.response.ExceptionResponse;
import com.test.change.response.Response;
import com.test.change.service.PersonaService;
import com.test.change.service.RolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/personas")
@CrossOrigin
@Api(value = "personas", produces = "application/json", tags = {"Controlador Servicio Persona"})
public class PersonaController {

    @Autowired
    RolService rolService;

    @Autowired
    PersonaService personaService;
    
    @ApiOperation(value = "Lista Personas", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<Response> lista() {
        return ResponseEntity.ok(personaService.lista());
    }

    @ApiOperation(value = "Lista de Roles", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @GetMapping(path = "/roles", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> listaRoles() {
        return ResponseEntity.ok(rolService.roles());
    }
    
    @ApiOperation(value = "Obtener Persona", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Response> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtener(id));
    }

    @ApiOperation(value = "Crear Persona", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/crea", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> crea(@RequestBody PersonaRequest request) {
        return ResponseEntity.ok(personaService.crear(request));
    }

    @ApiOperation(value = "Iniciar Sesion", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> login(@RequestBody RequestLogin login) {
        return ResponseEntity.ok(personaService.Login(login));
    }
    
    @ApiOperation(value = "Cerrar Sesion", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/logout", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> logout() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        PersonaPrincipal persona = (PersonaPrincipal) auth.getPrincipal();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new Response<>(null, "OK", HttpStatus.OK));
    }

    @ApiOperation(value = "Actualizar Personas", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/actualizar/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> actualizar(@PathVariable Long id, @RequestBody PersonaUpdateRequest request) {
        return ResponseEntity.ok(personaService.Actualizar(id, request));
    }

    @ApiOperation(value = "Eliminar Persona", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/eliminar/{id}", produces = "application/json")
    public ResponseEntity<Response> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.eliminar(id));
    }
}

package com.test.change.controller;

import com.test.change.request.PersonaRequest;
import com.test.change.request.RequestLogin;
import com.test.change.response.ExceptionResponse;
import com.test.change.response.Response;
import com.test.change.service.PersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/personas")
@CrossOrigin
@Api(value = "personas", produces = "application/json", tags = {"Controlador Servicio Persona"})
public class PersonaController {
    
    @Autowired
    PersonaService personaService;
    
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
        return ResponseEntity.ok(personaService.Login(login.getEmail(), login.getPassword()));
    }
}

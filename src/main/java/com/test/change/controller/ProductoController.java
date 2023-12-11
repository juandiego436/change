package com.test.change.controller;

import com.test.change.request.ProductoRequest;
import com.test.change.response.ExceptionResponse;
import com.test.change.response.Response;
import com.test.change.service.ProductoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("api/productos")
@CrossOrigin
@Api(value = "productos", produces = "application/json", tags = {"Controlador Servicio Productos"})
public class ProductoController {
    
    @Autowired
    ProductoService productoService;
    
    @ApiOperation(value = "Lista Personas", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<Response> lista() {
        return ResponseEntity.ok(productoService.lista());
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
        return ResponseEntity.ok(productoService.obtener(id));
    }

    @ApiOperation(value = "Crear Persona", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/crea", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> crea(@RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.crear(request));
    }

    @ApiOperation(value = "Actualizar Personas", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/actualizar/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> actualizar(@PathVariable Long id, @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.Actualizar(id, request));
    }

    @ApiOperation(value = "Iniciar Sesion", tags = {"Controlador Servicio Persona"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/eliminar/{id}", produces = "application/json")
    public ResponseEntity<Response> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.eliminar(id));
    }
}

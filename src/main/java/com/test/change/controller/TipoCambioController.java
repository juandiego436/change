
package com.test.change.controller;

import com.test.change.request.TipoCambioRequest;
import com.test.change.request.TransaccionRequest;
import com.test.change.response.ExceptionResponse;
import com.test.change.response.Response;
import com.test.change.service.TipoCambioService;
import com.test.change.service.TransaccionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/tipo-cambio")
@CrossOrigin
@Api(value = "TipoCambio", produces = "application/json", tags = {"Controlador Servicio TipoCambio"})
public class TipoCambioController {
    
    @Autowired
    TipoCambioService cambioService;
    
    @Autowired
    TransaccionService transaccionService;
    
    @ApiOperation(value = "Lista Tipos de Cambios", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @GetMapping(path = "/lista", produces = "application/json")
    public ResponseEntity<Response> tipoCambios() {
        return ResponseEntity.ok(cambioService.tipoCambio());
    }
    
    @ApiOperation(value = "Obtener por tipo moneda", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @GetMapping(path = "/{moneda}", produces = "application/json")
    public ResponseEntity<Response> obtenerMoneda(@PathVariable String moneda) {
        return ResponseEntity.ok(cambioService.tipoCambioMoneda(moneda));
    }
    
    @ApiOperation(value = "Crear Tipo Cambio", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/crea", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> crear(Authentication authentication,@RequestBody TipoCambioRequest request) {
        UserDetails userDeatails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(cambioService.crearTipoCambio(request,userDeatails));
    }
    
    @ApiOperation(value = "Actualizar Tipo Cambio", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PutMapping(path = "/actualiza/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> actualizar(Authentication authentication,@PathVariable Long id,@RequestBody TipoCambioRequest request) {
        UserDetails userDeatails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(cambioService.ActualizarTipoCambio(id, request,userDeatails));
    }
    
    @ApiOperation(value = "Generar Tipo de Cambio", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/generar", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> generar(@RequestBody TransaccionRequest request) {
        return ResponseEntity.ok(transaccionService.cambio(request));
    }
    
    @ApiOperation(value = "Generar todo los Tipo de Cambio", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/generar-cambios", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> generarCambios(@RequestBody TransaccionRequest request) {
        return ResponseEntity.ok(transaccionService.generarCambios(request));
    }
    
    @ApiOperation(value = "Crear Tipo Cambio", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PostMapping(path = "/registro", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> registro(Authentication authentication,@RequestBody TransaccionRequest request) {
        UserDetails userDeatails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(transaccionService.cambioGenerar(request,userDeatails));
    }
    
    @ApiOperation(value = "Actualiza Tipo Cambio", tags = {"Controlador Servicio TipoCambio"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Response.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Error en el Servidor", response = ExceptionResponse.class)
    })
    @PutMapping(path = "/actualizacion/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response> actualizacion(Authentication authentication,@PathVariable Long id,@RequestBody TransaccionRequest request) {
        UserDetails userDeatails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(transaccionService.Actualizar(id, request,userDeatails));
    }
}

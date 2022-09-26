package com.test.change.service;

import com.test.change.entity.Persona;
import com.test.change.request.PersonaRequest;
import com.test.change.response.Response;
import java.util.Optional;


public interface PersonaService {
    
    public Response crear(PersonaRequest request);
    public Optional<Persona> persona(String email);
    public Response Login(String email, String password);
    public Response Actualizar(Long id, PersonaRequest request);
    public Response eliminar(Long id);
    public Response lista();
}

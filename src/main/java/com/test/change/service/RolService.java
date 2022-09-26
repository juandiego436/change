package com.test.change.service;

import com.test.change.entity.Rol;
import com.test.change.response.Response;
import java.util.Optional;

public interface RolService {
    
    public Optional<Rol> getByRolNombre(String rolNombre);

    public void save(Rol rol);
    
    public Response roles();
}

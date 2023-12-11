package com.test.change.service.impl;

import com.test.change.entity.Rol;
import com.test.change.repository.RolRepository;
import com.test.change.response.Response;
import com.test.change.service.RolService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public Optional<Rol> getByRolNombre(String rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    @Override
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public Response roles() {
        try {
            var roles = rolRepository.findAll();
            return new Response(roles, "OK", HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error en roles ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

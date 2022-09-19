package com.test.change.service.impl;

import com.test.change.entity.Rol;
import com.test.change.repository.RolRepository;
import com.test.change.service.RolService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}

package com.test.change.repository;

import com.test.change.entity.Rol;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol,Long> {
    Optional<Rol> findByRolNombre(String rolNombre);
}

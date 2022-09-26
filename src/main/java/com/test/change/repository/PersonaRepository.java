package com.test.change.repository;

import com.test.change.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,Long> {
    Optional<Persona> findByEmail(String email);
    
    @Query("SELECT p FROM Persona p WHERE p.deletedAt IS NULL")
    Iterable<Persona> listado();
}

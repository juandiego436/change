package com.test.change.repository;

import com.test.change.entity.Transaccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends CrudRepository<Transaccion,Long> {
    
}

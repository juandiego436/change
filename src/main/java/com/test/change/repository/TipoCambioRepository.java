package com.test.change.repository;

import com.test.change.entity.TipoCambio;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends CrudRepository<TipoCambio, Long>{
   Optional<TipoCambio> findByMoneda(String moneda);
}

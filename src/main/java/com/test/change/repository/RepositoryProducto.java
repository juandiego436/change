package com.test.change.repository;

import com.test.change.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProducto extends CrudRepository<Producto,Long>{
    
}

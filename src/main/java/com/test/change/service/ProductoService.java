package com.test.change.service;

import com.test.change.request.ProductoRequest;
import com.test.change.response.Response;

public interface ProductoService {

    public Response crear(ProductoRequest request);

    public Response Actualizar(Long id, ProductoRequest request);

    public Response eliminar(Long id);

    public Response lista();

    public Response obtener(Long id);
}

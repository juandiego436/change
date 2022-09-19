package com.test.change.service;

import com.test.change.request.TransaccionRequest;
import com.test.change.response.Response;
import org.springframework.security.core.userdetails.UserDetails;


public interface TransaccionService {
    public Response cambioGenerar(TransaccionRequest request,UserDetails userDeatails);
    public Response cambio(TransaccionRequest request);
    public Response Actualizar(Long id,TransaccionRequest request,UserDetails userDeatails);
}

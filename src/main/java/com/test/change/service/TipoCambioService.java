package com.test.change.service;

import com.test.change.request.TipoCambioRequest;
import com.test.change.response.Response;
import org.springframework.security.core.userdetails.UserDetails;



public interface TipoCambioService {
    
    public Response tipoCambio();
    public Response crearTipoCambio(TipoCambioRequest request,UserDetails userDeatails);
    public Response ActualizarTipoCambio(Long id, TipoCambioRequest request,UserDetails userDeatails);
    public Response tipoCambioMoneda(String moneda);
}

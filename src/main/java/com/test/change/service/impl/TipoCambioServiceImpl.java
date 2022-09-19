package com.test.change.service.impl;

import com.test.change.entity.TipoCambio;
import com.test.change.repository.PersonaRepository;
import com.test.change.repository.TipoCambioRepository;
import com.test.change.repository.TransaccionRepository;
import com.test.change.request.TipoCambioRequest;
import com.test.change.response.Response;
import com.test.change.service.TipoCambioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {
    
    private static final Logger LOG = LoggerFactory.getLogger(TipoCambioServiceImpl.class);

    @Autowired
    TipoCambioRepository tipoCambioRepository;
    
    @Autowired
    TransaccionRepository transaccionRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Override
    public Response tipoCambio() {
        try{
            var result = tipoCambioRepository.findAll();
            if(!result.iterator().hasNext()){
                return new Response(null, "Sin Registros", HttpStatus.NO_CONTENT);
            }
            return new Response(result, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en tipoCambio ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response crearTipoCambio(TipoCambioRequest request,UserDetails userDeatails) {
        try{
            if(request.getCompra() == 0 && request.getVenta() == 0 && request.getMoneda().isEmpty()){
                return new Response(null,"Error en los campos", HttpStatus.BAD_REQUEST);
            }
            var persona = personaRepository.findByEmail(userDeatails.getUsername());
            if(!persona.isPresent()){
                return new Response(null,"No autorizado", HttpStatus.UNAUTHORIZED);
            }
            
            TipoCambio tp = new TipoCambio();
            tp.setCompra(request.getCompra());
            tp.setVenta(request.getVenta());
            tp.setMoneda(request.getMoneda());
            tp.setOrigen(request.getOrigen());
            tp.setPersonaCreacion(persona.get());
            tp.setPersonaActualizacion(null);
            
            var result = tipoCambioRepository.save(tp);
            return new Response(null, "OK", HttpStatus.CREATED);
        }catch(Exception ex){
            LOG.error("Error en crearTipoCambio ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response ActualizarTipoCambio(Long id, TipoCambioRequest request,UserDetails userDeatails) {
        try{
            TipoCambio tp = new TipoCambio();
            if(request.getCompra() == 0 && request.getVenta() == 0 && request.getMoneda().isEmpty()){
                return new Response(null,"Error en los campos", HttpStatus.BAD_REQUEST);
            }
            var persona = personaRepository.findByEmail(userDeatails.getUsername());
            
            if(!persona.isPresent()){
                return new Response(null,"No autorizado", HttpStatus.UNAUTHORIZED);
            }
            
            if(!tipoCambioRepository.findById(id).isPresent()){
                return new Response(null,"Error no se obtiene tipo de cambio", HttpStatus.NO_CONTENT);
            }
            
            tp = tipoCambioRepository.findById(id).get();
            tp.setCompra(request.getCompra());
            tp.setVenta(request.getVenta());
            tp.setMoneda(request.getMoneda());
            tp.setOrigen(request.getOrigen());
            tp.setPersonaActualizacion(persona.get());
            var result = tipoCambioRepository.save(tp);
//            if(result != null){
//                
//            }
            return new Response(result, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en ActualizarTipoCambio ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response tipoCambioMoneda(String moneda) {
        try{
            var result = tipoCambioRepository.findByMoneda(moneda);
            if(!result.isPresent()){
                return new Response(null, "No existe Moneda", HttpStatus.NO_CONTENT);
            }
            return new Response(result, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en tipoCambioMoneda ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

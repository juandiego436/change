package com.test.change.service.impl;

import com.test.change.entity.Transaccion;
import com.test.change.repository.PersonaRepository;
import com.test.change.repository.TipoCambioRepository;
import com.test.change.repository.TransaccionRepository;
import com.test.change.request.TransaccionRequest;
import com.test.change.response.Response;
import com.test.change.response.TipoCambioResponse;
import com.test.change.service.TransaccionService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService {
    
    private static final Logger LOG = LoggerFactory.getLogger(TransaccionServiceImpl.class);

    @Autowired
    TransaccionRepository transaccionRepository;
    
    @Autowired
    TipoCambioRepository tipoCambioRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Override
    public Response cambioGenerar(TransaccionRequest request,UserDetails userDeatails) {
        try{
            Double tipoCambioOrigen;
            Double tipoCambioDestino;
            if(request.getMonedaOrigen().isEmpty() && request.getMontoOrigen() == 0 && request.getMonedaDestino().isEmpty()){
                return new Response(null, "Error en los campos", HttpStatus.BAD_REQUEST);
            }
            
            var persona = personaRepository.findByEmail(userDeatails.getUsername());
            if(!persona.isPresent()){
                return new Response(null,"No autorizado", HttpStatus.UNAUTHORIZED);
            }
            
            var tpo = tipoCambioRepository.findByMoneda(request.getMonedaOrigen());
            var tpd = tipoCambioRepository.findByMoneda(request.getMonedaDestino());
            var result = new Transaccion();
            result.setMonedaOrigen(request.getMonedaOrigen());
            result.setMontoOrigen(request.getMontoOrigen());
            result.setMonedaDestino(request.getMonedaDestino());
           if(tpo.isPresent()){
                tipoCambioOrigen = tpo.get().getCompra();
                result.setMontoDestino(request.getMontoOrigen()*tipoCambioOrigen);
                result.setTipoCambio(tpo.get());
            }
            
            if(tpd.isPresent()){
                tipoCambioDestino = tpd.get().getVenta();
                result.setMontoDestino(request.getMontoOrigen()/tipoCambioDestino);
                result.setTipoCambio(tpd.get());
            }
            result.setPersonaCreacion(persona.get());
            result = transaccionRepository.save(result); 
            return new Response(result, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en cambioGenerar ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response cambio(TransaccionRequest request) {
        try{
            TipoCambioResponse response = new TipoCambioResponse();
            Double tipoCambioOrigen;
            Double tipoCambioDestino;
            
            if(request.getMonedaOrigen().isEmpty() && request.getMontoOrigen() == 0 && request.getMonedaDestino().isEmpty()){
                return new Response(null, "Error en los campos", HttpStatus.BAD_REQUEST);
            }
            
            var tpo = tipoCambioRepository.findByMoneda(request.getMonedaOrigen());
            var tpd = tipoCambioRepository.findByMoneda(request.getMonedaDestino());

            response.setFechaCreacion(new Date());
            response.setMonedaOrigen(request.getMonedaOrigen());
            response.setMontoOrigen(request.getMontoOrigen());
            response.setMonedaDestino(request.getMonedaDestino());
            if(tpo.isPresent()){
                tipoCambioOrigen = tpo.get().getCompra();
                response.setMontoDestino(request.getMontoOrigen()*tipoCambioOrigen);
                response.setTipoCambio(tipoCambioOrigen);
            }
            
            if(tpd.isPresent()){
                tipoCambioDestino = tpd.get().getVenta();
                response.setMontoDestino(request.getMontoOrigen()/tipoCambioDestino);
                response.setTipoCambio(tipoCambioDestino);
            }

            return new Response(response, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en cambio ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response Actualizar(Long id, TransaccionRequest request,UserDetails userDeatails) {
        try{
            Double tipoCambioOrigen;
            Double tipoCambioDestino;
            if(request.getMonedaOrigen().isEmpty() && request.getMontoOrigen() == 0 && request.getMonedaDestino().isEmpty()){
                return new Response(null, "Error en los campos", HttpStatus.BAD_REQUEST);
            }
            
            var persona = personaRepository.findByEmail(userDeatails.getUsername());
            if(!persona.isPresent()){
                return new Response(null,"No autorizado", HttpStatus.UNAUTHORIZED);
            }
            
            var tpo = tipoCambioRepository.findByMoneda(request.getMonedaOrigen());
            var tpd = tipoCambioRepository.findByMoneda(request.getMonedaDestino());
            if(transaccionRepository.existsById(id)){
                return new Response(null, "No existe Tipo de Cambio", HttpStatus.NO_CONTENT);
            }
            var result = transaccionRepository.findById(id).get();
            result.setMonedaOrigen(request.getMonedaOrigen());
            result.setMontoOrigen(request.getMontoOrigen());
            result.setMonedaDestino(request.getMonedaDestino());
           if(tpo.isPresent()){
                tipoCambioOrigen = tpo.get().getCompra();
                result.setMontoDestino(request.getMontoOrigen()*tipoCambioOrigen);
                result.setTipoCambio(tpo.get());
            }
            
            if(tpd.isPresent()){
                tipoCambioDestino = tpd.get().getVenta();
                result.setMontoDestino(request.getMontoOrigen()/tipoCambioDestino);
                result.setTipoCambio(tpd.get());
            }
            result.setPersonaActualizacion(persona.get());
            result = transaccionRepository.save(result);
            return new Response(result, "OK", HttpStatus.OK);
        }catch(Exception ex){
            LOG.error("Error en Actualizar ", ex);
            return new Response(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

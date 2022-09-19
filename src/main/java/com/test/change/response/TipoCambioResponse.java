package com.test.change.response;

import com.test.change.entity.TipoCambio;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioResponse {

    private String monedaOrigen;
    private double montoOrigen;
    private String monedaDestino;
    private double montoDestino;
    private double TipoCambio;
    private Date fechaCreacion;
}

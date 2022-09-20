
package com.test.change.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambiosResponse {
    private String monedaDestino;
    private double montoDestino;
    private double TipoCambio;
}

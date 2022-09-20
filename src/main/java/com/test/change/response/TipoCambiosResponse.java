
package com.test.change.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambiosResponse {
    private String monedaOrigen;
    private double montoOrigen;
    private List<CambiosResponse> cambios;
}


package com.test.change.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransaccionRequest {
    private String monedaOrigen;
    private double montoOrigen;
    private String monedaDestino;
}

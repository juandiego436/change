
package com.test.change.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TipoCambioRequest {
    private double compra;
    private double venta;
    private String origen;
    private String moneda;
}

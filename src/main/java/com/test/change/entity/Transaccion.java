package com.test.change.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaccion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true)
    private Integer id;
    
    @Column(name = "moneda_origen")
    private String monedaOrigen;
    
    @Column(name = "monto_origen",columnDefinition = "decimal(8,4) DEFAULT '0.0000'")
    private double montoOrigen;
    
    @Column(name = "moneda_destino")
    private String monedaDestino;
    
    @Column(name = "monto_destino",columnDefinition = "decimal(8,4) DEFAULT '0.0000'")
    private double montoDestino;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="tipo_cambio", referencedColumnName = "id", nullable = false)
    private TipoCambio TipoCambio;
            
    @Column(name = "fecha_creacion", updatable = false, columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date fechaCreacion;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="persona_creacion", referencedColumnName = "id", nullable = false)
    private Persona personaCreacion;
    
    @Column(name = "fecha_actualizacion", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date fechaActualizacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="persona_actualizacion", referencedColumnName = "id", nullable = true)
    private Persona personaActualizacion;
}

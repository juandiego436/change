package com.test.change.entity;

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
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true)
    private Long id;
    
    @Column(name = "compra", columnDefinition = "decimal(8,4) DEFAULT '0.0000'")
    private double compra;
    
    @Column(name = "venta", columnDefinition = "decimal(8,4) DEFAULT '0.0000'")
    private double venta;
    
    @Column(name = "origen", updatable = false, columnDefinition = "varchar(50)")
    private String origen;
    
    @Column(name = "moneda", updatable = false, columnDefinition = "varchar(50)")
    private String moneda;
    
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

package com.test.change.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true)
    private Long id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "apellido", length = 100)
    private String apellido;
    
    @Column(name = "edad")
    private Integer edad;
    
    @Column(name = "dni", length = 8)
    private String dni;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Rol roles;
    
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
    
    @Column(name = "deleted_at", nullable = true, columnDefinition = "TIMESTAMP")
    private Date deletedAt;
}

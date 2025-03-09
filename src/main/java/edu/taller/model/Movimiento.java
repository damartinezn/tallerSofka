package edu.taller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMovimientos;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, length = 10)
    private String tipoMovimiento;

    @Column(nullable = false)
    private Double valor;

    @Column
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "idCuenta", nullable = false) // Clave foránea en la tabla cuentas
    private Cuenta cuenta;

    @PrePersist
    private void prePersistFecha(){
        this.fecha = new Date();
    }


}

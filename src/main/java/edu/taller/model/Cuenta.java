package edu.taller.model;

import edu.taller.util.CodigoUnicoGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCuenta;

    @Column(nullable = false, length = 70)
    private String numeroCuenta;

    @Column(nullable = false, length = 10)
    private String tipoCuenta;

    @Column(nullable = false)
    private Double saldoInicial;

    @Column(nullable = false, length = 10)
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "idCliente"
    )
    private Cliente cliente;

    @PrePersist
    private void generarCuenta(){
        this.numeroCuenta = CodigoUnicoGenerator.generarCodigoDesdeUUID();
    }

}

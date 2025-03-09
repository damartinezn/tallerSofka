package edu.taller.model;

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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCliente;

    @Column(nullable = false, length = 70)
    private String contrasenia;

    @Column(nullable = false, length = 10)
    private String estado;

    @OneToOne(cascade = CascadeType.ALL)
    private Persona persona;

}

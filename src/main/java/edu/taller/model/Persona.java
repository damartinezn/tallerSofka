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
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPersona;

    @Column(nullable = false, length = 70)
    private String nombre;

    @Column(nullable = false, length = 70)
    private String genero;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false, length = 25)
    private String identificacion;

    @Column(nullable = false, length = 250)
    private String direccion;

    @Column(nullable = false, length = 25)
    private String telefono;


}

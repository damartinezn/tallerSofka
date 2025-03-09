package edu.taller.dto;

import edu.taller.model.Cliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonaDTO {

    @EqualsAndHashCode.Include
    private Integer idPersona;

    @NotNull
    @Size(min = 3, max = 70, message = "{nombre.tamaño}")
    private String nombre;

    @NotNull
    @Size(min = 3, max = 70, message = "{genero.tamaño}")
    private String genero;

    @NotNull
    private int edad;

    @NotNull
    @Size(min = 3, max = 25, message = "{identificacion.tamaño}")
    private String identificacion;

    @NotNull
    @Size(min = 3, max = 250, message = "{direccion.tamaño}")
    private String direccion;

    @NotNull
    @Size(min = 3, max = 25, message = "{telefono.tamaño}")
    private String telefono;

    @NotNull
    private Cliente cliente;

}

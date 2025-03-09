package edu.taller.dto;

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
public class ClienteDTO {

    @EqualsAndHashCode.Include
    private Integer idCliente;

    @NotNull
    @Size(min = 3, max = 70, message = "{contrasenia.tamaño}")
    private String contrasenia;

    @NotNull
    @Size(min = 3, max = 10, message = "{estado.tamaño}")
    private String estado;

    @NotNull
    private PersonaDTO persona;

}

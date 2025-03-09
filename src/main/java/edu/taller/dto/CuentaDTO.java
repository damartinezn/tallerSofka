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
public class CuentaDTO {

    @EqualsAndHashCode.Include
    private Integer idCuenta;

    @NotNull
    @Size(min = 3, max = 10, message = "{tipoCuenta.tamaño}")
    private String tipoCuenta;

    @NotNull
    private Double saldoInicial;

    @NotNull
    @Size(min = 3, max = 10, message = "{estado.tamaño}")
    private String estado;

    @NotNull
    private Integer idCliente;


}

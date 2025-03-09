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
public class MovimientoDTO {

    @EqualsAndHashCode.Include
    private Integer idMovimientos;

    @NotNull
    @Size(min = 3, max = 10, message = "{tipoMovimiento.tamaño}")
    private String tipoMovimiento;

    @NotNull
    private Double valor;

    @NotNull
    private Integer idCuenta;

}

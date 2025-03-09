package edu.taller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({ "cliente", "numeroCuenta", "tipoCuenta", "estadoCuenta", "saldoInicial", "fecha", "tipoMovimiento", "saldo","valor" })
public class ReporteMovimientoDTO {

    private Date   fecha;
    private String  tipoMovimiento;
    private Double  valor;
    private Double  saldo;
    private Double  saldoInicial;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private String estadoCuenta;

}

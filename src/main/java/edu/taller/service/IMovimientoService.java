package edu.taller.service;

import edu.taller.dto.CuentaDTO;
import edu.taller.dto.MovimientoDTO;
import edu.taller.model.Movimiento;
import org.springframework.http.ResponseEntity;

public interface IMovimientoService extends ICRUD<Movimiento, Integer> {

    ResponseEntity<?> saveMovimiento(MovimientoDTO movimientoDTO);

    ResponseEntity<?> updateMovimiento(Integer idMovimiento, MovimientoDTO movimiento);

    ResponseEntity<?> getReportMovimiento(String fechaInicio, String fechaFin);


}

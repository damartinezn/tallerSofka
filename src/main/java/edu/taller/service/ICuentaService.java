package edu.taller.service;

import edu.taller.dto.CuentaDTO;
import edu.taller.model.Cuenta;
import org.springframework.http.ResponseEntity;

public interface ICuentaService extends ICRUD<Cuenta, Integer> {

    ResponseEntity<?> saveCuenta(CuentaDTO cuenta);

    ResponseEntity<?> updateCuenta(Integer idCuenta, CuentaDTO cuenta);

    ResponseEntity<?> deleteCuenta(Integer idCuenta);

}

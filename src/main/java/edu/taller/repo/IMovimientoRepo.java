package edu.taller.repo;

import edu.taller.model.Movimiento;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMovimientoRepo extends IGenericRepo<Movimiento, Integer> {

    @Query(value = "SELECT saldo FROM movimiento WHERE id_cuenta = :idCuenta order by id_movimientos desc limit 1", nativeQuery = true)
    Double sumarSaldosPorCuentaSQL(Long idCuenta);

    @Query(value = "SELECT * FROM movimiento WHERE fecha BETWEEN CAST(:fechaInicio AS TIMESTAMP) AND CAST(:fechaFin AS TIMESTAMP)", nativeQuery = true)
    List<Movimiento> reportePorFechas(String fechaInicio, String fechaFin);

}

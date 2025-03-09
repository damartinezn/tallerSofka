package edu.taller.service.impl;

import edu.taller.dto.MovimientoDTO;
import edu.taller.dto.ReporteMovimientoDTO;
import edu.taller.model.Cuenta;
import edu.taller.model.Movimiento;
import edu.taller.repo.ICuentaRepo;
import edu.taller.repo.IGenericRepo;
import edu.taller.repo.IMovimientoRepo;
import edu.taller.service.IMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl extends CRUDImpl<Movimiento, Integer> implements IMovimientoService {

    private final IMovimientoRepo repo;
    private final ICuentaRepo repoCuenta;
    @Override
    protected IGenericRepo<Movimiento, Integer> getRepo() {
        return repo;
    }

    @Override
    public ResponseEntity<?> saveMovimiento(MovimientoDTO movimientoDTO) {
        Optional<Cuenta> cuentaExiste = repoCuenta.findById(movimientoDTO.getIdCuenta());
        if (!cuentaExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe la cuenta para realizar un movimiento.");
        }
        Double saldo = repo.sumarSaldosPorCuentaSQL(Long.valueOf(cuentaExiste.get().getIdCuenta()));
        saldo = (saldo != null) ? saldo : 0.0;

        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setCuenta(cuentaExiste.get());
        saldo = (saldo == 0.0) ?
                (movimientoDTO.getValor() + cuentaExiste.get().getSaldoInicial())
                : (saldo + movimientoDTO.getValor());
        if (saldo < 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La cuenta no cuenta con saldo para realizar la transacción.");
        }
        movimiento.setSaldo(saldo);
        Movimiento movimientoSave = repo.save(movimiento);
        if (movimientoSave == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo registrar el movimiento.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoSave);
    }

    @Override
    public ResponseEntity<?> updateMovimiento(Integer idMovimiento, MovimientoDTO movimiento) {
        Optional<Movimiento> movExiste = repo.findById(idMovimiento);
        if (!movExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el movimiento para actualizar.");
        }
        Optional<Cuenta> cuentaExiste = repoCuenta.findById(movimiento.getIdCuenta());
        if (!cuentaExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe la cuenta para realizar un movimiento.");
        }
        if (movExiste.get().getCuenta().getIdCuenta() != movimiento.getIdCuenta()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede movilizar el movimiento porque la cuenta no es la misma.");
        }

        Movimiento movimientoA = movExiste.get();
        Double saldo = movimientoA.getSaldo();
        movimientoA.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoA.setCuenta(cuentaExiste.get());
        saldo = (movimientoA.getValor() > movimiento.getValor()) ?
                (saldo - (movimientoA.getValor() - movimiento.getValor()))
                : ((movimientoA.getValor() < movimiento.getValor()) ?
                (saldo + (movimiento.getValor() - movimientoA.getValor()))
                : (saldo));

        if (saldo < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cuenta no cuenta con saldo para realizar la transacción.");
        }
        movimientoA.setSaldo(saldo);
        movimientoA.setValor(movimiento.getValor());
        Movimiento movimientoSave = repo.save(movimientoA);
        if (movimientoSave == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo registrar el movimiento.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movimientoSave);
    }

    @Override
    public ResponseEntity<?> getReportMovimiento(String fechaInicio, String fechaFin) {
        List<Movimiento> lista = repo.reportePorFechas(fechaInicio, fechaFin);
        if (lista.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                        .body("No se encontraron movimientos para las fechas ingresadas.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transformar(lista));
    }

    private List<ReporteMovimientoDTO> transformar(List<Movimiento> lista){
        List<ReporteMovimientoDTO> listaTransformada = new ArrayList<>();
        for (Movimiento movimiento : lista) {
            ReporteMovimientoDTO aux = new ReporteMovimientoDTO();
            aux.setFecha(movimiento.getFecha());
            aux.setTipoMovimiento(movimiento.getTipoMovimiento());
            aux.setValor(movimiento.getValor());
            aux.setSaldo(movimiento.getSaldo());
            aux.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
            aux.setCliente(movimiento.getCuenta().getCliente().getPersona().getNombre());
            aux.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
            aux.setEstadoCuenta(movimiento.getCuenta().getEstado());
            aux.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
            listaTransformada.add(aux);
        }
        return  listaTransformada;
    }

}

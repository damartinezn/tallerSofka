package edu.taller.service.impl;

import edu.taller.dto.CuentaDTO;
import edu.taller.model.Cliente;
import edu.taller.model.Cuenta;
import edu.taller.repo.IClienteRepo;
import edu.taller.repo.ICuentaRepo;
import edu.taller.repo.IGenericRepo;
import edu.taller.service.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl extends CRUDImpl<Cuenta, Integer> implements ICuentaService {

    private final ICuentaRepo repo;
    private final IClienteRepo repoCliente;

    @Override
    protected IGenericRepo<Cuenta, Integer> getRepo() {
        return repo;
    }


    @Override
    public ResponseEntity<?>  saveCuenta(CuentaDTO cuenta) {
        Optional<Cliente> cliente = repoCliente.findById(cuenta.getIdCliente());
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado para el id: "
                            + cuenta.getIdCliente().toString());
        }
        Cuenta ca = new Cuenta();
        ca.setCliente(cliente.get());
        ca.setEstado(cuenta.getEstado());
        ca.setTipoCuenta(cuenta.getTipoCuenta());
        ca.setSaldoInicial(cuenta.getSaldoInicial());
        Cuenta cuentaSave = repo.save(ca);
        if (cuentaSave != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaSave);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Existe un error al crear la cuenta.");
        }
    }

    @Override
    public ResponseEntity<?> updateCuenta(Integer idCuenta, CuentaDTO cuenta) {
        Optional<Cuenta> cuentaAux = repo.findById(idCuenta);
        if (!cuentaAux.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cuenta no encontrado para el id: "
                                + idCuenta);
        }
        Optional<Cliente> cliente = repoCliente.findById(
                                    cuentaAux.get().getCliente().getIdCliente());
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado para el id: "
                                + cuentaAux.get().getCliente().getIdCliente());
        }
        if (cliente.get().getIdCliente() !=  cuenta.getIdCliente()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede cambiar de cliente para una cuenta: "
                                + cuenta.getIdCliente().toString());
        }
        Cuenta ca = cuentaAux.get();
        ca.setEstado(cuenta.getEstado());
        ca.setTipoCuenta(cuenta.getTipoCuenta());
        ca.setSaldoInicial(cuenta.getSaldoInicial());
        Cuenta cuentaSave = repo.save(ca);
        if (cuentaSave != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cuentaSave);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Existe un error al crear la cuenta.");
        }
    }

    @Override
    public ResponseEntity<?> deleteCuenta(Integer idCuenta) {
        Optional<Cuenta> cuentaAux = repo.findById(idCuenta);
        if (!cuentaAux.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cuenta no encontrado para el id: "
                            + idCuenta);
        }
        try {
            repo.delete(cuentaAux.get());
            return ResponseEntity.status(HttpStatus.OK).body("Cuenta Eliminada");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("La cuenta posee movimientos registrados");
        }
    }

}

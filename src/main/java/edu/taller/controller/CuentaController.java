package edu.taller.controller;

import edu.taller.dto.CuentaDTO;
import edu.taller.model.Cuenta;
import edu.taller.service.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final ICuentaService service;

    @GetMapping
    public List<Cuenta> buscarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cuenta buscarPorId(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody CuentaDTO cuenta){
        return service.saveCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Integer id, @RequestBody CuentaDTO cuenta){ return service.updateCuenta(id,cuenta); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        service.delete(id);
    }
}

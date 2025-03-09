package edu.taller.controller;

import edu.taller.dto.MovimientoDTO;
import edu.taller.model.Movimiento;
import edu.taller.service.IMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final IMovimientoService service;

    @GetMapping
    public List<Movimiento> buscarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Movimiento buscarPorId(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody MovimientoDTO movimiento){ return service.saveMovimiento(movimiento); }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Integer id, @RequestBody MovimientoDTO movimiento){
        return service.updateMovimiento(id,movimiento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        service.delete(id);
    }
}

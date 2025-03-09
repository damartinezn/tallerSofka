package edu.taller.controller;

import edu.taller.service.IMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final IMovimientoService service;

    @GetMapping
    public ResponseEntity<?> getReportMovimiento(@RequestParam("fecha") String fechaRango) {
        String[] fechas = fechaRango.split(",");
        if (fechas.length != 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Formato de fecha incorrecto. Debe ser: fechaInicio,fechaFin");
        }
        return service.getReportMovimiento(fechas[0], fechas[1]);
    }

}

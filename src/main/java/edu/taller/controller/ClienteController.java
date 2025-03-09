package edu.taller.controller;

import edu.taller.dto.ClienteDTO;
import edu.taller.model.Cliente;
import edu.taller.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService service;

    @GetMapping
    public List<Cliente> buscarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody ClienteDTO cliente){ return service.saveCliente(cliente); }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Integer id, @RequestBody ClienteDTO cliente){ return service.updateCliente(id,cliente);   }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id){  return service.deleteCliente(id); }
}

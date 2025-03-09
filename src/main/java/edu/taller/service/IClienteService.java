package edu.taller.service;

import edu.taller.dto.ClienteDTO;
import edu.taller.model.Cliente;
import org.springframework.http.ResponseEntity;

public interface IClienteService extends ICRUD<Cliente, Integer> {

    ResponseEntity<?> saveCliente(ClienteDTO clienteDTO);
    ResponseEntity<?> updateCliente(Integer idCliente, ClienteDTO clienteDTO);

    ResponseEntity<?> deleteCliente(Integer idCliente);
}

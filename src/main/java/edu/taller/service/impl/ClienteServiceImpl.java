package edu.taller.service.impl;

import edu.taller.dto.ClienteDTO;
import edu.taller.model.Cliente;
import edu.taller.model.Persona;
import edu.taller.repo.IClienteRepo;
import edu.taller.repo.IGenericRepo;
import edu.taller.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

    private final IClienteRepo repo;

    @Override
    protected IGenericRepo<Cliente, Integer> getRepo() {
        return repo;
    }

    @Override
    public ResponseEntity<?> saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        persona.setNombre(clienteDTO.getPersona().getNombre());
        persona.setGenero(clienteDTO.getPersona().getGenero());
        persona.setEdad(clienteDTO.getPersona().getEdad());
        persona.setIdentificacion(clienteDTO.getPersona().getIdentificacion());
        persona.setDireccion(clienteDTO.getPersona().getDireccion());
        persona.setTelefono(clienteDTO.getPersona().getTelefono());

        cliente.setContrasenia(clienteDTO.getContrasenia());
        cliente.setEstado(clienteDTO.getEstado());
        cliente.setPersona(persona);

        Cliente clienteGuardado = repo.save(cliente);
        if (clienteGuardado == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo registrar el cliente.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    @Override
    public ResponseEntity<?> updateCliente(Integer idCliente, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteAux = repo.findById(idCliente);
        if (!clienteAux.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado para el id: " + idCliente);
        }

        Cliente clienteAnterior = clienteAux.get();
        clienteAnterior.setContrasenia(clienteDTO.getContrasenia());
        clienteAnterior.setEstado(clienteDTO.getEstado());
        Persona anterior = clienteAnterior.getPersona();
        anterior.setNombre(clienteDTO.getPersona().getNombre());
        anterior.setGenero(clienteDTO.getPersona().getGenero());
        anterior.setEdad(clienteDTO.getPersona().getEdad());
        anterior.setIdentificacion(clienteDTO.getPersona().getIdentificacion());
        anterior.setDireccion(clienteDTO.getPersona().getDireccion());
        anterior.setTelefono(clienteDTO.getPersona().getTelefono());

        clienteAnterior.setPersona(anterior);
        Cliente save = repo.save(clienteAnterior);
        if (save == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo registrar el cliente.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }
}

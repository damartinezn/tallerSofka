package edu.taller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.taller.model.Cliente;
import edu.taller.model.Persona;
import edu.taller.service.IClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearCliente_CuandoDatosValidos_RetornaClienteCreado() throws Exception {
        // Arrange: Simulación de datos de entrada y respuesta esperada
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        cliente.setContrasenia("PruebaTest");
        cliente.setEstado("True");

        persona.setNombre("PruebaUno");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("1720743713");
        persona.setDireccion("Quito ecuador calderon");
        persona.setTelefono("0992455762");
        cliente.setPersona(persona);

        Cliente clienteRespuesta = new Cliente();
        clienteRespuesta.setIdCliente(1);
        clienteRespuesta.setContrasenia("PruebaTest");
        clienteRespuesta.setEstado("True");
        clienteRespuesta.setPersona(persona);

        when(clienteService.save(cliente)).thenReturn(clienteRespuesta);

        mockMvc.perform(post("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(1))
                .andExpect(jsonPath("$.contrasenia").value("PruebaTest"))
                .andExpect(jsonPath("$.estado").value("True"))
                .andExpect(jsonPath("$.persona.nombre").value("PruebaUno"))
                .andExpect(jsonPath("$.persona.genero").value("Masculino"))
                .andExpect(jsonPath("$.persona.edad").value(30))
                .andExpect(jsonPath("$.persona.identificacion").value("1720743713"))
                .andExpect(jsonPath("$.persona.direccion").value("Quito ecuador calderon"))
                .andExpect(jsonPath("$.persona.telefono").value("0992455762"));
    }


    @Test
    void editarCliente_CuandoExiste_RetornaClienteEditado() throws Exception {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        Persona personaUpdate = new Persona();
        cliente.setContrasenia("PruebaTest");
        cliente.setEstado("True");

        persona.setNombre("PruebaUno");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("1720743713");
        persona.setDireccion("Quito ecuador calderon");
        persona.setTelefono("0992455762");
        cliente.setPersona(persona);

        Cliente clienteRespuesta = new Cliente();
        clienteRespuesta.setIdCliente(1);
        clienteRespuesta.setContrasenia("PruebaTest");
        clienteRespuesta.setEstado("True");

        personaUpdate.setNombre("PruebaDos");
        personaUpdate.setGenero("Masculino");
        personaUpdate.setEdad(31);
        personaUpdate.setIdentificacion("1720743713");
        personaUpdate.setDireccion("Guayaquil");
        personaUpdate.setTelefono("0992455762");
        clienteRespuesta.setPersona(personaUpdate);

        when(clienteService.updade(1, cliente)).thenReturn(clienteRespuesta);

        mockMvc.perform(put("/clientes/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persona.nombre").value("PruebaDos"))
                .andExpect(jsonPath("$.persona.edad").value(31))
                .andExpect(jsonPath("$.persona.direccion").value("Guayaquil"));
    }

    @Test
    void eliminarCliente_CuandoExiste_RetornaNoContent() throws Exception {
        doNothing().when(clienteService).delete(1);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerClientePorId_CuandoExiste_RetornaCliente() throws Exception {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        cliente.setContrasenia("PruebaTest");
        cliente.setEstado("True");
        cliente.setIdCliente(1);
        persona.setNombre("PruebaUno");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("1720743713");
        persona.setDireccion("Quito ecuador calderon");
        persona.setTelefono("0992455762");
        cliente.setPersona(persona);

        when(clienteService.findById(1)).thenReturn(cliente);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(1))
                .andExpect(jsonPath("$.persona.nombre").value("PruebaUno"))
                .andExpect(jsonPath("$.persona.telefono").value("0992455762"));
    }


    @Test
    void listarTodosLosClientes_RetornaListaClientes() throws Exception {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        cliente.setContrasenia("PruebaTest");
        cliente.setEstado("True");

        persona.setNombre("PruebaUno");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("1720743713");
        persona.setDireccion("Quito ecuador calderon");
        persona.setTelefono("0992455762");
        cliente.setPersona(persona);

        when(clienteService.findAll()).thenReturn(Arrays.asList(cliente));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].persona.nombre").value("PruebaUno"));
    }

}

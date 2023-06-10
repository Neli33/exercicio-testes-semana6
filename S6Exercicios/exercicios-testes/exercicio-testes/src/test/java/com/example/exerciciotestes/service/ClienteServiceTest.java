package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteService clienteService;

    //Exercício1
    @Test
    void buscaTodosClientes() {
        List<Cliente> clienteMock = List.of(
                new Cliente("João Silva", 200.0),
                new Cliente("Maria Oliveira", 150.00),
                new Cliente("Sofia Machado", 30.00)
        );

        when(clienteRepository.findAll()).thenReturn(clienteMock);

        List<Cliente> clientesResultado = clienteService.buscaTodosClientes();

        assertNotNull(Collections.unmodifiableList(clientesResultado));
        assertEquals(clienteMock.get(0).getNomeCliente(), clientesResultado.get(0).getNomeCliente());

        verify(clienteRepository).findAll();
    }

    //Exrcício 2
    @Test
    void salvarCliente() {

        Cliente clienteMock =
                new Cliente("Paulo Coelho", 60.0);


        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);


        Cliente clienteResultado = clienteService.salvarCliente(
                new ClienteRequest("Paulo Coelho", 60.0)
        );

        assertNotNull(clienteResultado);
        assertEquals(clienteMock.getNomeCliente(), clienteResultado.getNomeCliente());
        verify(clienteRepository).save(any());

    }


    //Exercício 3
    @Test
    void atualizarCliente() {
        Cliente clienteMock = new Cliente("João Paulo", 500.0 );
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));

        Cliente atualizarCliente =
                clienteRepository.save(new Cliente("João Paulo", 500.0));

        assertNotNull(atualizarCliente);
        assertEquals(clienteMock.getId(), atualizarCliente.getId());
        assertEquals(clienteMock.getNomeCliente(), atualizarCliente.getNomeCliente());
        assertEquals(clienteMock.getSaldoCliente(), atualizarCliente.getSaldoCliente());

        verify(clienteRepository).save(any());
        verify(clienteRepository).findById(anyLong());


    }

    //Exrecício1
    @Test
    void buscaClientePorId() {
        Long id = 1L;

        Cliente clienteMock = new Cliente();
        clienteMock.setId(id);
        clienteMock.setNomeCliente("Martin Silva");
        clienteMock.setSaldoCliente(1000.0);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteMock));


        Cliente clienteEncontrado = clienteService.buscaClientePorId(id);

        assertNotNull(clienteEncontrado);
        assertEquals(id, clienteEncontrado.getId());
        assertEquals(clienteMock.getNomeCliente(), clienteEncontrado.getNomeCliente());
        assertEquals(clienteMock.getSaldoCliente(), clienteEncontrado.getSaldoCliente());
    }

    //Exercício4

    @Test
    void detelaClientePorId() {
        Long clienteId = 1L;
        when(clienteRepository.buscaClientePorId(clienteId)).thenReturn(null);

        assertThrows(HttpClientErrorException.class, () -> clienteService.detelaClientePorId(clienteId));
        verify(clienteRepository, never()).deleteById(clienteId);
    }
    }








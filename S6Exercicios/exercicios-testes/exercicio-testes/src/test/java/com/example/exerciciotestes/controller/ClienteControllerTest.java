package com.example.exerciciotestes.controller;

import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ClienteRepository clienteRepository;

    @Test
    void getAllCliente() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/clientes")
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.content().json(
                """
                        []
                        """
        ));


    }

    @Test
    void getClienteById() {
    }

    @Test
    void saveCliente() throws Exception{
        Cliente clienteMock =
                new Cliente(1L,
                        "aa",
                        10.0
                       );

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);


        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/clientes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """ 
                                {
                                  
                                                       "nomeCliente":"aa",
                                                       "saldoCliente":10.0
                                    
                                }
                                """
                        )
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        ).andExpect(MockMvcResultMatchers.content().json(
                """
                {
                   
                       "nomeCliente":"aa",
                       "saldoCliente":10.0
                   
                }
                """
        ));
    }
}
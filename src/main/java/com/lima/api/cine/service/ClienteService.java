package com.lima.api.cine.service;

import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarOuSalvar(Cliente cliente) {

        Cliente clienteModel;
        Optional<Cliente> optionalCliente = clienteRepository.findByCpf(cliente.getCpf());
        if(optionalCliente.isEmpty()){
            clienteModel = clienteRepository.save(cliente);
            return clienteModel;
        }
        return optionalCliente.get();
    }
}

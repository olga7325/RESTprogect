package com.RESTproject.RESTproject.controller;

import com.RESTproject.RESTproject.entity.Client;
import com.RESTproject.RESTproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientResource {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }


    @GetMapping("/clients/{id}")
    public Client retrieveClient(@PathVariable long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent()) {
            throw new ClientNotFoundException("id-" + id);
        }

        return client.get();
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable long id) {

        clientRepository.deleteById(id);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client, @PathVariable long id) {

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent())
            return ResponseEntity.notFound().build();

        client.setId(id);

        clientRepository.save(client);

        return ResponseEntity.noContent().build();
    }



}

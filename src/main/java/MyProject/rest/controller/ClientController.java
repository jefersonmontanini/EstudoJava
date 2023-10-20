package MyProject.rest.controller;

import MyProject.domain.entity.Client;
import MyProject.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private Clients clients;

    @GetMapping("/{id}")
    public Client get(@PathVariable Integer id) {
        return clients
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Client> find( Client filter) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, exampleMatcher);
        return clients.findAll(example);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client) {
        return clients.save(client);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void update(@PathVariable Integer id,
                       @RequestBody Client client) {
         clients
                .findById(id)
                .map( client1 -> {
                    client.setId(client1.getId());
                    clients.save(client);
                    return client1;
                } ).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clients.findById(id)
                .map(client1 -> {
                    clients.delete(client1);
                    return client1;
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel deletar o cliente") );
    }
}

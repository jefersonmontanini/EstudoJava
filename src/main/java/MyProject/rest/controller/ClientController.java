package MyProject.rest.controller;

import MyProject.domain.entity.Client;
import MyProject.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private Clients clients;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable Integer id) {
        Optional<Client> client = clients.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok((client.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity find( Client filter) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, exampleMatcher);
        List<Client> list = clients.findAll(example);
        return ResponseEntity.ok(list);
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Client client) {
        Client client1 = clients.save(client);
        return ResponseEntity.ok(client1);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Client client) {
        return clients
                .findById(id)
                .map( client1 -> {
                    client.setId(client1.getId());
                    clients.save(client);
                    return ResponseEntity.noContent().build();
                } ).orElseGet( () -> ResponseEntity.notFound().build() );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Client> client = clients.findById(id);
        if (client.isPresent()) {
            clients.delete(client.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

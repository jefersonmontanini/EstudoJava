package MyProject.rest.controller;

import MyProject.domain.entity.Client;
import MyProject.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Client client) {
        Client client1 = clients.save(client);
        return ResponseEntity.ok(client1);
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

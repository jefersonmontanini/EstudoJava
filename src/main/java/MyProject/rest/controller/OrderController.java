package MyProject.rest.controller;

import MyProject.domain.entity.Order;
import MyProject.domain.repository.Orders;
import MyProject.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private Orders orders;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("{id}")
    public Order getById(@PathVariable Integer id) {
        return orders.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar esse pedido"));
    }

    @GetMapping
    public List<Order> getBy(Order filter) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, exampleMatcher);
        return orders.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order save(Order order) {
        return orders.save(order);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id,
                       @RequestBody Order order) {
        orders.findById(id)
                .map( order1 -> {
                    order.setId(order1.getId());
                    orders.save(order);
                    return order1;
                } ).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        orders.findById(id)
                .map(order -> {
                    orders.delete(order);
                    return order;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar esse pedido"));
    }
}

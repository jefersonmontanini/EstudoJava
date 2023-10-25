package MyProject.rest.controller;

import MyProject.domain.entity.Order;
import MyProject.domain.repository.Orders;
import MyProject.rest.dto.OrderDTO;
import MyProject.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


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
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Não foi possivel encontrar esse pedido"));
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
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderDTO dto) {
        Order order = orderServiceImpl.save(dto);
        return order.getId();
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id,
                       @RequestBody Order order) {
        orders.findById(id)
                .map( order1 -> {
                    order.setId(order1.getId());
                    orders.save(order);
                    return order1;
                } ).orElseThrow( ()-> new ResponseStatusException(NOT_FOUND) );
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        orders.findById(id)
                .map(order -> {
                    orders.delete(order);
                    return order;
                }).orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Não foi possivel encontrar esse pedido"));
    }
}

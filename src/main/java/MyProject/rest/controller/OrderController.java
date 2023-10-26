package MyProject.rest.controller;

import MyProject.domain.entity.Item;
import MyProject.domain.entity.Order;
import MyProject.domain.repository.Orders;
import MyProject.exception.BusinessRulesException;
import MyProject.rest.dto.InfoItemsDTO;
import MyProject.rest.dto.InfoOrderDTO;
import MyProject.rest.dto.OrderDTO;
import MyProject.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private Orders orders;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("{id}")
    public InfoOrderDTO getOrderById(@PathVariable Integer id) {
        return orderServiceImpl
                .getOrder(id)
                .map(order ->
                    orderConvert(order)
                )
                .orElseThrow( ()-> new ResponseStatusException(NOT_FOUND, "Nao foi encontrado nenhum pedido com este id"));
    }

    private InfoOrderDTO orderConvert(Order order) {
        return InfoOrderDTO
                .builder()
                .idCode(order.getId())
                .dateOrder(order.getDate_order().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient_id().getCpf())
                .name(order.getClient_id().getName())
                .total(order.getTotal())
                .items(itemsConvert(order.getItems()))
                .build();
    }

    private List<InfoItemsDTO> itemsConvert(List<Item> item) {
        if (CollectionUtils.isEmpty(item)){
            return Collections.emptyList();
        }                                                   //se o objeto esta vazio ele retorna um array vazio para evitar bugs

        return item.stream()
                .map(findedItem -> {
            return InfoItemsDTO.builder()
                    .product(findedItem.getProduct_id().getName())
                    .unityPrice(findedItem.getProduct_id().getPrice())
                    .quantity(findedItem.getQuantity())
                    .build();
        }).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderDTO dto) {
        Order order = orderServiceImpl.save(dto);
        return order.getId();
    }
}

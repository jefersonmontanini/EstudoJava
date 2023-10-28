package MyProject.rest.controller;

import MyProject.domain.entity.Item;
import MyProject.domain.entity.Order;
import MyProject.domain.enums.StateOrder;
import MyProject.domain.repository.Orders;
import MyProject.exception.BusinessRulesException;
import MyProject.rest.dto.InfoItemsDTO;
import MyProject.rest.dto.InfoOrderDTO;
import MyProject.rest.dto.OrderDTO;
import MyProject.rest.dto.UpdateStateDTO;
import MyProject.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.Format;
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
    public InfoOrderDTO getById(@PathVariable Integer id) {
        return orderServiceImpl
                .getAllById(id)
                .map(order -> convertOrderInDTO(order))
                .orElseThrow( ()-> new ResponseStatusException(NOT_FOUND) );
    }

    private InfoOrderDTO convertOrderInDTO (Order order) {
        return InfoOrderDTO.builder()
                .idCode(order.getId())
                .name(order.getClient_id().getName())
                .cpf(order.getClient_id().getCpf())
                .total(order.getTotal())
                .dateOrder(order.getDate_order().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .status(order.getStatus().name())
                .items(convertItemsInDTO(order.getItems()))
                .build();
    }

    private List<InfoItemsDTO> convertItemsInDTO(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(item ->
            InfoItemsDTO.builder()
                    .product(item.getProduct_id().getName())
                    .unityPrice(item.getProduct_id().getPrice())
                    .quantity(item.getQuantity())
                    .build()
        ).collect(Collectors.toList());
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderDTO dto) {
        Order order = orderServiceImpl.save(dto);
        return order.getId();
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody UpdateStateDTO dto) {



        String state = dto.getNewState();
        orderServiceImpl.updateState(id, StateOrder.valueOf(state));                                    //pega o valor da variavel de cima e valida se existe enum com o mesmo valor

    }
}

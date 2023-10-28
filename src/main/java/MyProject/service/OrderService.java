package MyProject.service;

import MyProject.domain.entity.Order;
import MyProject.domain.enums.StateOrder;
import MyProject.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO dto);

    Optional<Order> getAllById(Integer id);

    void updateState(Integer id, StateOrder status);
}

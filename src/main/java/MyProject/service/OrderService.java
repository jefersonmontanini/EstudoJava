package MyProject.service;

import MyProject.domain.entity.Order;
import MyProject.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO dto);

    Optional<Order> getOrder(Integer id);
}

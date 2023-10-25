package MyProject.service;

import MyProject.domain.entity.Order;
import MyProject.rest.dto.OrderDTO;

public interface OrderService {

    Order save(OrderDTO dto);
}

package MyProject.service.impl;

import MyProject.domain.repository.Orders;
import MyProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Orders repository;

    public OrderServiceImpl(Orders repository) {
        this.repository = repository;
    }
}

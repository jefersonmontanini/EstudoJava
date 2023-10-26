package MyProject.service.impl;

import MyProject.domain.entity.Client;
import MyProject.domain.entity.Item;
import MyProject.domain.entity.Order;
import MyProject.domain.entity.Product;
import MyProject.domain.repository.Clients;
import MyProject.domain.repository.Items;
import MyProject.domain.repository.Orders;
import MyProject.domain.repository.Products;
import MyProject.exception.BusinessRulesException;
import MyProject.rest.dto.ItemDTO;
import MyProject.rest.dto.OrderDTO;
import MyProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Orders orders;

    @Autowired
    private Clients clients;

    @Autowired
    private Products products;

    @Autowired
    private Items items;

    public OrderServiceImpl(Orders repository) {
        this.orders = repository;
    }

    @Override
    public Order save(OrderDTO dto) {
        Integer idClient = dto.getClient();                                         //Pega o ID do cliente
        Client client = clients
                .findById(idClient)                                                 //busca o cliente pelo ID
                .orElseThrow(() -> new BusinessRulesException("não foi possivel encontrar esse clinte"));

        Order order = new Order();
        order.setTotal(dto.getTotal());
        order.setDate_order(LocalDate.now());
        order.setClient_id(client);

        List<Item> item = convertItems(order, dto.getItems());
        orders.save(order);
        items.saveAll(item);
        order.setItems(item);

        return order;
    }


    private List<Item> convertItems(Order order, List<ItemDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRulesException("Não é possivel realizar pedido sem items.");
        }

        return items
                .stream()
                .map( itemDTO -> {
                    Integer idProduct = itemDTO.getProduct();
                    Product product = products
                            .findById(idProduct)
                            .orElseThrow(() -> new BusinessRulesException("Nã foi possivel encontrar esse produto:" +idProduct));

                    Item item = new Item();
                    item.setQuantity(itemDTO.getQuantity());
                    item.setOrder_id(order);
                    item.setProduct_id(product);
                    return item;
                }).collect(Collectors.toList());
    }


    @Override
    public Optional<Order> getOrder(Integer id) {
        return orders.findByIdFetch(id);
    }
}

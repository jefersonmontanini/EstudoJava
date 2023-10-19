package MyProject.domain.repository;

import MyProject.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Orders extends JpaRepository<Order, Integer> {                                         //primeiro parametro a classe que ela representa e o segundo o tipo da primary key estabelecido no banco de dados
}

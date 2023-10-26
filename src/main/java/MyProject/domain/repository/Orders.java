package MyProject.domain.repository;

import MyProject.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Orders extends JpaRepository<Order, Integer> {                                         //primeiro parametro a classe que ela representa e o segundo o tipo da primary key estabelecido no banco de dados

    @Query("SELECT p FROM Order p LEFT JOIN FETCH p.items WHERE p.id = :id")
    Optional<Order> findByIdFetchItems(@Param("id") Integer id);
}

package MyProject.domain.repository;

import MyProject.domain.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Clients extends JpaRepository<Client, Integer> {                                           //primeiro parametro a classe que ela representa e o segundo o tipo da primary key estabelecido no banco de dados

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.orders WHERE c.id = :id ")
    public Client getClientFetchOrder(@Param("id") Integer id);

}

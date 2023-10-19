package MyProject.domain.repository;

import MyProject.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Items extends JpaRepository<Item, Integer> {                                  //primeiro parametro a classe da entidade que ela representa e o segundo o tipo da primary key estabelecido no banco de dados
}

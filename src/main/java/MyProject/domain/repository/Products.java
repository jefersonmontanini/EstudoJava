package MyProject.domain.repository;

import MyProject.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Products extends JpaRepository<Product, Integer> {                                 //primeiro parametro a classe que ela representa e o segundo o tipo da primary key estabelecido no banco de dados

}

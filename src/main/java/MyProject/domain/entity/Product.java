package MyProject.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Data                                                                       //Data contêm getters setters e outros
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")                                                     //NAME: nome do campo no banco de dados
    private Integer id;
    @Column
    private String name;
    @Column
    private BigDecimal price;

    @OneToMany(mappedBy = "product_id", fetch = FetchType.LAZY)                                      //refere-se a variavel que indica a tabela NA APLICAÇÃO || FETCH LAZY faz com q as buscas de product n tragam Item
    private Set<Item> items;

}

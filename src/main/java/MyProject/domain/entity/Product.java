package MyProject.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

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

    @OneToMany(mappedBy = "product_id")                                      //refere-se a variavel que indica a tabela NA APLICAÇÃO
    private Set<Item> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

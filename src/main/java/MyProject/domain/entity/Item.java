package MyProject.domain.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")                                          //NAME: nome do campo no banco de dados
    private Order order_id;                                                 //variavel q nomeia o campo da tabela na aplicação

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    @Column
    private  Integer quantity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package MyProject.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "`Order`")
public class Order {

    @Id                                                                 //define qual a primary key da entidade ----------- obrigatório em uma entidade
    @GeneratedValue(strategy =  GenerationType.AUTO)                    // representa o auto-incremento
    @Column(name = "id")                                                //assim como anotation TABLE pode nomear a coluna, UNIQUE entre outros
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")                                     //NAME: nome do campo no banco de dados
    private Client client_id;                                           //variavel q nomeia o campo da tabela na aplicação

    @Column
    private LocalDate date_order;

    @Column( precision = 20, scale = 2)                             //precision quantidade de casas, scale quantidade de casas após a virgula
    private BigDecimal total;

    @OneToMany(mappedBy = "order_id", fetch = FetchType.LAZY)                                  //nome da variavel usada pela APLICAÇÃO || FETCH LAZY faz com q as buscas de product n tragam Item
    private Set<Item> items;

    public Order() {
    }

    public Order(Client client_id, LocalDate date_order, BigDecimal total) {
        this.client_id = client_id;
        this.date_order = date_order;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public LocalDate getDate_order() {
        return date_order;
    }

    public void setDate_order(LocalDate date_order) {
        this.date_order = date_order;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date_order=" + date_order +
                ", total=" + total +
                '}';
    }
}

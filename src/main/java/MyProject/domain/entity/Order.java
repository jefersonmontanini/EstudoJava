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

    @Column(length = 20, precision = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "order_id" )                                  //nome da variavel usada pela APLICAÇÃO
    private Set<Item> items;

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
}

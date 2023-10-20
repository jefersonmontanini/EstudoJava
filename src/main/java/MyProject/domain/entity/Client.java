package MyProject.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Client {

    @Id                                                                 //define qual a primary key da entidade ----------- obrigatório em uma entidade
    @GeneratedValue(strategy =  GenerationType.AUTO)                    // representa o auto-incremento
    @Column(name = "id")                                                //assim como anotation TABLE pode nomear a coluna, UNIQUE entre outros
    private Integer id;
    @Column(name = "name", length = 100)                                //NAME: nome do campo no banco de dados
    private String name;                                                //variavel q nomeia o campo da tabela na aplicação

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "client_id", fetch = FetchType.LAZY)                                  //nome da variavel usada pela APLICAÇÃO || FETCH LAZY faz com q as buscas de product n tragam Item
    private Set<Order> orders;

    public Client() {}

    public Client(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

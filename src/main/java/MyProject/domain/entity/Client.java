package MyProject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Data                                                                       //Data contêm getters setters e outros
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Client {

    @Id                                                                 //define qual a primary key da entidade ----------- obrigatório em uma entidade
    @GeneratedValue(strategy =  GenerationType.AUTO)                    // representa o auto-incremento
    @Column(name = "id")                                                //assim como anotation TABLE pode nomear a coluna, UNIQUE entre outros
    private Integer id;

    @NotEmpty(message = "{field.name.required}")
    @Column(name = "name", length = 100)                                //NAME: nome do campo no banco de dados
    private String name;                                                //variavel q nomeia o campo da tabela na aplicação

    @NotEmpty(message = "{field.cpf.required}")
    @CPF(message = "{field.cpf.invalid}")
    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "client_id", fetch = FetchType.LAZY)                                  //nome da variavel usada pela APLICAÇÃO || FETCH LAZY faz com q as buscas de product n tragam Item
    private Set<Order> orders;

    public Client(String name) {
        this.name = name;
    }

}

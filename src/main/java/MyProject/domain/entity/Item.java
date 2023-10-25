package MyProject.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data                                                                       //Data contêm getters setters e outros
@AllArgsConstructor
@NoArgsConstructor
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

}

package MyProject.rest.dto;

import MyProject.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder                                                                //vai gerar um objeto builder para poder criar uma instancia do proprio objeto
public class InfoOrderDTO {

    private Integer idCode;
    private String name;
    private String cpf;
    private BigDecimal total;
    private String dateOrder;
    private String status;
    private List<InfoItemsDTO> items;
}

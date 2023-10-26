package MyProject.rest.dto;

import MyProject.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoItemsDTO {

    private String product;
    private BigDecimal unityPrice;
    private Integer quantity;
}

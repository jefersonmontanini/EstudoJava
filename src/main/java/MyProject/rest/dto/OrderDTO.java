package MyProject.rest.dto;

import MyProject.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "{field.idClient.required}")
    private Integer client;

    @NotNull(message = "{field.totalOrder.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.itemsOrder.required}")
    private List<ItemDTO> items;
}

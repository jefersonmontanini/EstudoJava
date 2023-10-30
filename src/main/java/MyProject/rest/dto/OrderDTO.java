package MyProject.rest.dto;

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

    @NotNull(message = "Informe o cliente")
    private Integer client;

    @NotNull(message = "Campo total é obrigatorio")
    private BigDecimal total;


    private List<ItemDTO> items;
}

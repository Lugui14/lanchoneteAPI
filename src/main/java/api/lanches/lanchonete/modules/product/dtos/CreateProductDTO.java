package api.lanches.lanchonete.modules.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(
        @NotBlank
        String product,
        @NotNull
        double price,
        @NotBlank
        String description,
        @NotNull
        Long idcategory
) {
}

package api.lanches.lanchonete.modules.product.dtos;

import jakarta.validation.constraints.NotNull;

public record ProductDTO(@NotNull Long idproduct,
                         String product,
                         @NotNull double price,
                         String description,
                         @NotNull Long idcategory,
                         boolean isproductactive) {
}

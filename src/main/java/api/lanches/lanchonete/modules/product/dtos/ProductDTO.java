package api.lanches.lanchonete.modules.product.dtos;

import jakarta.validation.constraints.NotNull;

public record ProductDTO(@NotNull Long idproduct,
                         String product,
                         double price,
                         String description,
                         Long idcategory,
                         boolean isproductactive) {
}

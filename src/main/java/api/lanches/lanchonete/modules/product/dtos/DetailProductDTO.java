package api.lanches.lanchonete.modules.product.dtos;

import api.lanches.lanchonete.modules.category.infra.Category;

public record DetailProductDTO(
        Long idproduct,
        String product,
        double price,
        String description,
        boolean isproductactive,
        Category category
) {
    public DetailProductDTO(ProductDTO data, Category category) {
        this(data.idproduct(), data.product(), data.price(), data.description(), data.isproductactive(), category);
    }
}

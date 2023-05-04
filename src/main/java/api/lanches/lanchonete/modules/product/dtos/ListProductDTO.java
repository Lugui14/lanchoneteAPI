package api.lanches.lanchonete.modules.product.dtos;

import api.lanches.lanchonete.modules.product.infra.Product;

public record ListProductDTO(Long idproduct, String product, double price, String description, String category) {

    public ListProductDTO(Product product) {
        this(product.getIdproduct(),
                product.getProduct(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory().getCategory());
    }
}

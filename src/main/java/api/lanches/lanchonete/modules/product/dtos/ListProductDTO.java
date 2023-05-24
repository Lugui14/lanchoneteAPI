package api.lanches.lanchonete.modules.product.dtos;

import api.lanches.lanchonete.modules.product.infra.Product;

public record ListProductDTO(Long idproduct, String product, double price, String description, boolean isactive, String category) {

    public ListProductDTO(Product product) {
        this(product.getIdproduct(),
                product.getProduct(),
                product.getPrice(),
                product.getDescription(),
                product.isIsproductactive(),
                product.getCategory().getCategory());
    }
}

package api.lanches.lanchonete.modules.request.dtos;

import api.lanches.lanchonete.modules.product.infra.Product;

public record DetailRequestDTO(Product product, int requeststatus) {
}

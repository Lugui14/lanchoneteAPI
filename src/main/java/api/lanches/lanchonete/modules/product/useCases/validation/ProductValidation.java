package api.lanches.lanchonete.modules.product.useCases.validation;

import api.lanches.lanchonete.modules.product.dtos.ProductDTO;

public interface ProductValidation {
    void validate(ProductDTO data);

}

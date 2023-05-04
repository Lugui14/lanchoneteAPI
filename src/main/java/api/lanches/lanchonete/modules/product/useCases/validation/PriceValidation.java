package api.lanches.lanchonete.modules.product.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.product.dtos.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class PriceValidation implements ProductValidation {

    public void validate(ProductDTO data) {
        if(data.price() <= 0) {
            throw new ValidationException("Preço invalido.");
        }
    }
}

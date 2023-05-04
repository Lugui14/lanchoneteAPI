package api.lanches.lanchonete.modules.request.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.product.infra.ProductRepository;
import api.lanches.lanchonete.modules.request.dtos.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductExistsValidation implements RequestValidation{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void validate(RequestDTO data) {
        if(!productRepository.existsProductByIdproductAndIsproductactiveTrue(data.idproduct())) {
            throw new ValidationException("Produto não existe ou está inativo.");
        }
    }
}

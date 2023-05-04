package api.lanches.lanchonete.modules.product.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.category.infra.CategoryRepository;
import api.lanches.lanchonete.modules.product.dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryExistsValidation implements ProductValidation {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void validate(ProductDTO data) {
        if(data.idcategory() == null || !categoryRepository.existsCategoryByIdcategoryAndIscategoryactiveTrue(data.idcategory())) {
            throw new ValidationException("A Categoria é invalida ou não existe.");
        }
    }
}

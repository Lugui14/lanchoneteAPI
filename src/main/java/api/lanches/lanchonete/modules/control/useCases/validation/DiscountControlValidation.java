package api.lanches.lanchonete.modules.control.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.dtos.ControlDTO;
import org.springframework.stereotype.Component;

@Component
public class DiscountControlValidation implements ControlValidation {

    @Override
    public void validate(ControlDTO data) {

        if(data.discount() < 0 || data.discount() > 0.5) {
            throw new ValidationException("O desconto especificado é invalido");
        }

    }
}

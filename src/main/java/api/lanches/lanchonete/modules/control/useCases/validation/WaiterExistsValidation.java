package api.lanches.lanchonete.modules.control.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.dtos.ControlDTO;
import api.lanches.lanchonete.modules.waiter.infra.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaiterExistsValidation implements ControlValidation{

    @Autowired
    private WaiterRepository waiterRepository;


    @Override
    public void validate(ControlDTO data) {
        if(!waiterRepository.existsById(data.idwaiter())) {
            throw new ValidationException("O Garçom especificado não existe");
        }
    }
}

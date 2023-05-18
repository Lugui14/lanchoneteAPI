package api.lanches.lanchonete.modules.control.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.dtos.ControlDTO;
import api.lanches.lanchonete.modules.control.infra.ControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsControlNumberValidation implements ControlValidation {

    @Autowired
    protected ControlRepository controlRepository;

    @Override
    public void validate(ControlDTO data) {
        if(controlRepository.existsAllByControlnumberAndIsclosedFalse(data.controlnumber())) {
            throw new ValidationException("Esse numero de comanda já está sendo usado");
        }
    }
}

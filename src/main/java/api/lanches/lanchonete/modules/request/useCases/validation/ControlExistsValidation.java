package api.lanches.lanchonete.modules.request.useCases.validation;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.infra.ControlRepository;
import api.lanches.lanchonete.modules.request.dtos.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlExistsValidation implements RequestValidation{

    @Autowired
    private ControlRepository controlRepository;

    @Override
    public void validate(RequestDTO data) {
        if(!controlRepository.existsByIdcontrolAndIsclosedFalse(data.idcontrol())) {
            throw new ValidationException("Comanda não existe ou ja foi fechada.");
        }
    }
}

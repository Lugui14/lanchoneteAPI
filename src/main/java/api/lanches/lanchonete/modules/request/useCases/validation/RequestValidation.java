package api.lanches.lanchonete.modules.request.useCases.validation;

import api.lanches.lanchonete.modules.request.dtos.RequestDTO;

public interface RequestValidation {

    void validate(RequestDTO data);

}

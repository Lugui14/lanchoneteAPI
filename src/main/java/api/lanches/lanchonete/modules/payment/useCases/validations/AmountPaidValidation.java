package api.lanches.lanchonete.modules.payment.useCases.validations;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.infra.ControlRepository;
import api.lanches.lanchonete.modules.payment.dtos.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmountPaidValidation implements PaymentValidation {

    @Autowired
    private ControlRepository controlRepository;

    @Override
    public void validate(PaymentDTO data) {

        if(controlRepository.existsControlByIdcontrolAndIsclosedFalse(data.idcontrol())) {
            throw new ValidationException("A comanda especificada não existe ou está fechada");
        }

        var control = controlRepository.getReferenceById(data.idcontrol());

        if(data.amountpaid() < 0 || data.amountpaid() > control.getTopay()) {
            throw new ValidationException("Valor pago é invalido");
        }
    }
}

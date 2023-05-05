package api.lanches.lanchonete.modules.payment.useCases.validations;

import api.lanches.lanchonete.modules.payment.dtos.PaymentDTO;

public interface PaymentValidation {

    void validate(PaymentDTO data);

}

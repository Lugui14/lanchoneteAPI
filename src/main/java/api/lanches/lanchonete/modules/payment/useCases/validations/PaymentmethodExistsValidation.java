package api.lanches.lanchonete.modules.payment.useCases.validations;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.payment.dtos.PaymentDTO;
import api.lanches.lanchonete.modules.paymentmethod.infa.PaymentmethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentmethodExistsValidation implements PaymentValidation{

    @Autowired
    private PaymentmethodRepository paymentmethodRepository;

    @Override
    public void validate(PaymentDTO data) {
        if(!paymentmethodRepository.existsById(data.idpaymentmethod())) {
            throw new ValidationException("Metodo de pagamento não existe");
        }
    }
}

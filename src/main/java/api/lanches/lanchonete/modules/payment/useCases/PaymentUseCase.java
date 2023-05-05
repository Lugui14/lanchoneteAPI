package api.lanches.lanchonete.modules.payment.useCases;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.infra.ControlRepository;
import api.lanches.lanchonete.modules.payment.dtos.*;
import api.lanches.lanchonete.modules.payment.infra.Payment;
import api.lanches.lanchonete.modules.payment.infra.PaymentRepository;
import api.lanches.lanchonete.modules.payment.useCases.validations.PaymentValidation;
import api.lanches.lanchonete.modules.paymentmethod.infa.PaymentmethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentUseCase {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentmethodRepository paymentmethodRepository;

    @Autowired
    private ControlRepository controlRepository;

    @Autowired
    private List<PaymentValidation> validation;
    public ListPaymentDTO create(CreatePaymentDTO data) {

        validation.forEach(v -> v.validate(new PaymentDTO(
                null,
                data.amountpaid(),
                data.paymentdetail(),
                data.idcontrol(),
                data.idpaymentmethod()
        )));

        var control = controlRepository.getReferenceById(data.idcontrol());
        var paymentmethod = paymentmethodRepository.getReferenceById(data.idpaymentmethod());

        var payment = new Payment(null, data.amountpaid(), data.paymentdetail(), control, paymentmethod);

        paymentRepository.save(payment);

        return new ListPaymentDTO(payment);
    }

    public Page<ListPaymentDTO> list(Long idcontrol, Pageable pageable) {

        if(!controlRepository.existsById(idcontrol)) {
            throw new ValidationException("A comanda especificada não existe");
        }

        return paymentRepository
                .findAllByControl(controlRepository.getReferenceById(idcontrol), pageable)
                .map(ListPaymentDTO::new);
    }

    public ListPaymentDTO update(UpdatePaymentDTO data) {

        if(!paymentRepository.existsById(data.idpayment())) {
            throw new ValidationException("Pagamento especificado não existe");
        }

        var payment = paymentRepository.getReferenceById(data.idpayment());

        validation.forEach(v -> v.validate(new PaymentDTO(
                null,
                data.amountpaid(),
                data.paymentdetail(),
                payment.getControl().getIdcontrol(),
                data.idpaymentmethod()
        )));

        payment.update(new DetailPaymentDTO(payment));

        return new ListPaymentDTO(payment);
    }

    public void delete(Long idpayment) {
        if(!paymentRepository.existsById(idpayment)) {
            throw new ValidationException("Pagamento especificado não existe");
        }

        var payment = paymentRepository.getReferenceById(idpayment);

        if(payment.getControl().isIsclosed()) {
            throw new ValidationException("A comanda desse pagamento ja está fechada");
        }

        paymentRepository.delete(payment);
    }

}

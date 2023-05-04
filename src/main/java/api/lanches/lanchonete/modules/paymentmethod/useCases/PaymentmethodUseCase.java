package api.lanches.lanchonete.modules.paymentmethod.useCases;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.paymentmethod.dtos.PaymentmethodDTO;
import api.lanches.lanchonete.modules.paymentmethod.infa.Paymentmethod;
import api.lanches.lanchonete.modules.paymentmethod.infa.PaymentmethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class PaymentmethodUseCase {

    @Autowired
    private PaymentmethodRepository repository;

    public Page<PaymentmethodDTO> list(Pageable pageable) {
        return repository.findAll(pageable).map(PaymentmethodDTO::new);
    }

    public PaymentmethodDTO create(String method) {
        var paymethod = new Paymentmethod(method.toUpperCase(new Locale("pt", "BR")));
        repository.save(paymethod);

        return new PaymentmethodDTO(paymethod);
    }

    public void delete(Long idmethod) {

        if(!repository.existsById(idmethod)) {
            throw new ValidationException("O metodo de pagamento nao existe");
        }

        repository.deleteById(idmethod);
    }
}

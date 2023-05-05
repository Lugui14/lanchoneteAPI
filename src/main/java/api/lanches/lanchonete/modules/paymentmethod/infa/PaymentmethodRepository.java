package api.lanches.lanchonete.modules.paymentmethod.infa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentmethodRepository extends JpaRepository<Paymentmethod, Long> {
    boolean existsPaymentmethodByPaymentmethod(String method);
}

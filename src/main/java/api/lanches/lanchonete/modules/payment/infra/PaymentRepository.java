package api.lanches.lanchonete.modules.payment.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findAllByControl(Control control, Pageable pageable);
}

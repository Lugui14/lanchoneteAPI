package api.lanches.lanchonete.modules.payment.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
import api.lanches.lanchonete.modules.payment.dtos.DetailPaymentDTO;
import api.lanches.lanchonete.modules.paymentmethod.infa.Paymentmethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "payment")
@Entity(name = "Payment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idpayment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpayment;
    private double amountpaid;
    private String paymentdetail;

    @ManyToOne
    @JoinColumn(name = "idcontrol", nullable = false)
    private Control control;

    @ManyToOne
    @JoinColumn(name = "idpaymentmethod", nullable = false)
    private Paymentmethod paymentmethod;

    public void update(DetailPaymentDTO data) {
        if(data.amountpaid() != 0) {
            this.amountpaid = data.amountpaid();
        }

        if(data.paymentdetail() != null) {
            this.paymentdetail = data.paymentdetail();
        }

        if(data.paymentmethod() != null) {
            this.paymentmethod = data.paymentmethod();
        }
    }

}

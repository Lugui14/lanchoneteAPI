package api.lanches.lanchonete.modules.payment.dtos;

import api.lanches.lanchonete.modules.control.infra.Control;
import api.lanches.lanchonete.modules.payment.infra.Payment;
import api.lanches.lanchonete.modules.paymentmethod.infa.Paymentmethod;

public record DetailPaymentDTO(double amountpaid,
                               String paymentdetail,
                               Control control,
                               Paymentmethod paymentmethod) {

    public DetailPaymentDTO(Payment payment) {
        this(payment.getAmountpaid(),
                payment.getPaymentdetail(),
                payment.getControl(),
                payment.getPaymentmethod());
    }

}

package api.lanches.lanchonete.modules.payment.dtos;

import api.lanches.lanchonete.modules.payment.infra.Payment;

public record ListPaymentDTO(Long idcontrol,
                             Long idpayment,
                             double amountpaid,
                             String paymentdetail,
                             String paymentmethod) {

    public ListPaymentDTO(Payment payment) {
        this(payment.getControl().getIdcontrol(),
                payment.getIdpayment(),
                payment.getAmountpaid(),
                payment.getPaymentdetail(),
                payment.getPaymentmethod().getPaymentmethod());
    }

}

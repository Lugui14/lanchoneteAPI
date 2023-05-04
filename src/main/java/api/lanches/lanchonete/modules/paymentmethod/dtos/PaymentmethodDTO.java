package api.lanches.lanchonete.modules.paymentmethod.dtos;

import api.lanches.lanchonete.modules.paymentmethod.infa.Paymentmethod;

public record PaymentmethodDTO(Long idpaymentmethod, String paymentmethod, double valMoved) {
    public PaymentmethodDTO(Paymentmethod paymentmethod) {
        this(paymentmethod.getIdpaymentmethod(), paymentmethod.getPaymentmethod(), paymentmethod.getValmoved());
    }
}

package api.lanches.lanchonete.modules.payment.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdatePaymentDTO(@NotNull Long idpayment,
                               double amountpaid,
                               String paymentdetail,
                               Long idpaymentmethod) {
}

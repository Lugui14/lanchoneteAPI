package api.lanches.lanchonete.modules.payment.dtos;

import jakarta.validation.constraints.NotNull;

public record CreatePaymentDTO(@NotNull double amountpaid,
                               String paymentdetail,
                               @NotNull Long idcontrol,
                               @NotNull Long idpaymentmethod) {
}

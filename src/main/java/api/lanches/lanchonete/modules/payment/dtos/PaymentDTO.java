package api.lanches.lanchonete.modules.payment.dtos;

public record PaymentDTO(Long idpayment,
                         double amountpaid,
                         String paymentdetail,
                         Long idcontrol,
                         Long idpaymentmethod) {

}

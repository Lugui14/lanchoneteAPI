package api.lanches.lanchonete.modules.payment.useCases;

import api.lanches.lanchonete.modules.payment.dtos.CreatePaymentDTO;
import api.lanches.lanchonete.modules.payment.dtos.ListPaymentDTO;
import api.lanches.lanchonete.modules.payment.dtos.UpdatePaymentDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/payment")
public class PaymentControl {

    @Autowired
    private PaymentUseCase paymentUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<ListPaymentDTO> create(@RequestBody @Valid CreatePaymentDTO data, UriComponentsBuilder uriBuilder) {
        var payment = paymentUseCase.create(data);
        var uri = uriBuilder.path("/payment/{idpayment}").buildAndExpand(payment.idpayment()).toUri();

        return ResponseEntity.created(uri).body(payment);
    }

    @GetMapping("/{idcontrol}")
    public ResponseEntity<Page<ListPaymentDTO>> list(@PathVariable Long idcontrol, Pageable pageable) {
        var page = paymentUseCase.list(idcontrol, pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListPaymentDTO> update(@RequestBody @Valid UpdatePaymentDTO data) {
        var payment = paymentUseCase.update(data);

        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{idpayment}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long idpayment) {
        paymentUseCase.delete(idpayment);

        return ResponseEntity.ok().build();
    }
}

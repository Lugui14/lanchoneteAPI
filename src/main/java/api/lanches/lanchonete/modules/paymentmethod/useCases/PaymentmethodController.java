package api.lanches.lanchonete.modules.paymentmethod.useCases;

import api.lanches.lanchonete.modules.paymentmethod.dtos.PaymentmethodDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paymentmethod")
@SecurityRequirement(name = "bearer-key")
public class PaymentmethodController {

    @Autowired
    private PaymentmethodUseCase paymentmethodUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentmethodDTO> create(@RequestBody @NotBlank String paymentmethod, UriComponentsBuilder uriBuilder) {
        var method = paymentmethodUseCase.create(paymentmethod);
        var uri = uriBuilder.path("/paymentmethod/{idpaymentmethod}")
                .buildAndExpand(method.idpaymentmethod())
                .toUri();

        return ResponseEntity.created(uri).body(method);
    }

    @GetMapping
    public ResponseEntity<Page<PaymentmethodDTO>> list(Pageable pageable) {
        var page = paymentmethodUseCase.list(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{idpaymentmethod}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long idpaymentmethod) {
        paymentmethodUseCase.delete(idpaymentmethod);

        return ResponseEntity.ok().build();
    }
}

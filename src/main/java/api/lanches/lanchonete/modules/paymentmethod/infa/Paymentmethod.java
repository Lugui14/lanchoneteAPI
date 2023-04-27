package api.lanches.lanchonete.modules.paymentmethod.infa;

import api.lanches.lanchonete.modules.payment.infra.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "paymentmethod")
@Entity(name = "Paymentmethod")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idpaymentmethod")
public class Paymentmethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idpaymentmethod;
    private String paymentmethod;
    private double valmoved;

    @OneToMany(mappedBy = "paymentmethod")
    private List<Payment> payment;
}

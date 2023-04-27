package api.lanches.lanchonete.modules.control.infra;

import api.lanches.lanchonete.modules.payment.infra.Payment;
import api.lanches.lanchonete.modules.request.infra.Request;
import api.lanches.lanchonete.modules.waiter.infra.Waiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "control")
@Entity(name = "Control")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idcontrol")
public class Control {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcontrol;
    private String client;
    private float topay;
    private float discount;
    private boolean isclosed;

    @Temporal(TemporalType.TIMESTAMP)
    private Date entrace;

    @ManyToOne
    @JoinColumn(name = "idwaiter", nullable = false)
    private Waiter waiter;

    @OneToMany(mappedBy = "control")
    private List<Request> request;

    @OneToMany(mappedBy = "control")
    private List<Payment> payment;

}

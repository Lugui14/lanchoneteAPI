package api.lanches.lanchonete.modules.control.infra;

import api.lanches.lanchonete.modules.control.dtos.DetailControlDTO;
import api.lanches.lanchonete.modules.payment.infra.Payment;
import api.lanches.lanchonete.modules.request.infra.Request;
import api.lanches.lanchonete.modules.waiter.infra.Waiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "control")
@Entity(name = "Control")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idcontrol")
public class Control {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontrol;
    private String client;
    private double topay;
    private double discount;
    private boolean isclosed;
    private int controlnumber;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime entrace;

    @ManyToOne
    @JoinColumn(name = "idwaiter", nullable = false)
    private Waiter waiter;

    @OneToMany(mappedBy = "control")
    private List<Request> request;

    @OneToMany(mappedBy = "control")
    private List<Payment> payment;

    public Control(String client, int controlnumber, Waiter waiter) {
        this.client = client;
        this.topay = 0;
        this.discount = 0;
        this.isclosed = false;
        this.controlnumber = controlnumber;
        this.entrace = LocalDateTime.now();
        this.waiter = waiter;
    }

    public void update(DetailControlDTO data) {
        if(data.client() != null) {
            this.client = data.client();
        }

        if(data.controlnumber() != 0) {
            this.controlnumber = data.controlnumber();
        }

        if(data.discount() != this.discount) {
            this.discount = data.discount();
        }

        if(data.waiter() != null) {
            this.waiter = data.waiter();
        }
    }

    public void delete() { this.isclosed = true; }
}

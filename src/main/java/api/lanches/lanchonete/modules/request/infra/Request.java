package api.lanches.lanchonete.modules.request.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
import api.lanches.lanchonete.modules.product.infra.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "request")
@Entity(name = "Request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idrequest")
public class Request {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idrequest;
    private int requeststatus;
    private float vlvenda;

    @ManyToOne
    @JoinColumn(name = "idproduct", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idcontrol", nullable = false)
    private Control control;

}

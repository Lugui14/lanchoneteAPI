package api.lanches.lanchonete.modules.request.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
import api.lanches.lanchonete.modules.product.infra.Product;
import api.lanches.lanchonete.modules.request.dtos.DetailRequestDTO;
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
    private Long idrequest;
    private int requeststatus;
    private double vlvenda;

    @ManyToOne
    @JoinColumn(name = "idproduct", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idcontrol", nullable = false)
    private Control control;

    public void update(DetailRequestDTO data) {
        if(data.product() != null) {
            this.product = data.product();
        }

        if(data.requeststatus() != this.requeststatus) {
            this.requeststatus = data.requeststatus();
        }
    }
}

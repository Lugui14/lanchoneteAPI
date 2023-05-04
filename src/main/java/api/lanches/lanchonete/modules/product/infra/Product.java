package api.lanches.lanchonete.modules.product.infra;

import api.lanches.lanchonete.modules.category.infra.Category;
import api.lanches.lanchonete.modules.product.dtos.DetailProductDTO;
import api.lanches.lanchonete.modules.request.infra.Request;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "product")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idproduct")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduct;
    private String product;
    private double price;
    private String description;
    private boolean isproductactive;

    @ManyToOne
    @JoinColumn(name = "idcategory", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Request> request;

    public void updateProduct(DetailProductDTO data) {
        if(data.product() != null) {
            this.product = data.product();
        }

        if(data.price() != 0) {
            this.price = data.price();
        }

        if(data.description() != null) {
            this.description = data.description();
        }

        if(data.category() != null) {
            this.category = data.category();
        }

        if(data.isproductactive()) {
            this.isproductactive = true;
        }
    }

    public void delete() { this.isproductactive = false; }

}

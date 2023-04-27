package api.lanches.lanchonete.modules.product.infra;

import api.lanches.lanchonete.modules.category.infra.Category;
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
    private long idproduct;
    private String product;
    private float price;
    private String description;
    private boolean isproductactive;

    @ManyToOne
    @JoinColumn(name = "idcategory", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Request> request;

}

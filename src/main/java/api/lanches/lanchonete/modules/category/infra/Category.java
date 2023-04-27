package api.lanches.lanchonete.modules.category.infra;

import api.lanches.lanchonete.modules.category.dtos.CreateCategoryDTO;
import api.lanches.lanchonete.modules.product.infra.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "category")
@Entity(name = "Category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idcategory")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcategory;
    private String category;
    private String description;
    private boolean iscategoryactive;

    @OneToMany(mappedBy = "category")
    private List<Product> product;

    public Category(CreateCategoryDTO data) {
        this.category = data.category();
        this.description = data.description();
        this.iscategoryactive = isIscategoryactive();
    }


}

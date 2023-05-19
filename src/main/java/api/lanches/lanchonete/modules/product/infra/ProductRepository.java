package api.lanches.lanchonete.modules.product.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByIsproductactiveTrue(Pageable page);

    Page<Product> findAllByIsproductactiveFalse(Pageable page);

    boolean existsProductByIdproductAndIsproductactiveTrue(Long idproduct);

    boolean existsProductByProduct(String product);
}

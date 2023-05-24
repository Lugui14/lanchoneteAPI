package api.lanches.lanchonete.modules.category.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAllByIscategoryactiveTrue(Pageable page);

    Page<Category> findAllByIscategoryactiveFalse(Pageable page);

    boolean existsCategoryByIdcategoryAndIscategoryactiveTrue(Long idcategory);
}

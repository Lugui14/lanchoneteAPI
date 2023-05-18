package api.lanches.lanchonete.modules.category.useCases;

import api.lanches.lanchonete.modules.category.dtos.CreateCategoryDTO;
import api.lanches.lanchonete.modules.category.dtos.ListCategoriesDTO;
import api.lanches.lanchonete.modules.category.dtos.UpdateCategoryDTO;
import api.lanches.lanchonete.modules.category.infra.Category;
import api.lanches.lanchonete.modules.category.infra.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Service
public class CategoryUseCase {

    @Autowired
    private CategoryRepository repository;

    public Category create(CreateCategoryDTO data) {
        var category = new Category(data);
        repository.save(category);

        return category;
    }

    public Page<ListCategoriesDTO> list(Pageable pageable) {
        return repository.findAllByIscategoryactiveTrue(pageable).map(ListCategoriesDTO::new);
    }

    public Category update(UpdateCategoryDTO data) {
        var category = repository.getReferenceById(data.idcategory());
        category.updateData(data);

        return category;
    }

    public void delete(long idcategory) {
        var category = repository.getReferenceById(idcategory);
        category.delete();
    }

}

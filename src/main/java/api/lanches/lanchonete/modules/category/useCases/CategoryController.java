package api.lanches.lanchonete.modules.category.useCases;

import api.lanches.lanchonete.modules.category.dtos.CreateCategoryDTO;
import api.lanches.lanchonete.modules.category.dtos.ListCategoriesDTO;
import api.lanches.lanchonete.modules.category.dtos.UpdateCategoryDTO;
import api.lanches.lanchonete.modules.category.infra.Category;
import api.lanches.lanchonete.modules.category.infra.CategoryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CreateCategoryDTO data) {
        repository.save(new Category(data));
    }

    @GetMapping
    public Page<ListCategoriesDTO> list(Pageable page) {
        return repository.findAll(page).map(ListCategoriesDTO::new);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateCategoryDTO data) {

    }

}

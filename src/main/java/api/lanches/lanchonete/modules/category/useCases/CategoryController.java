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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ListCategoriesDTO> create(@RequestBody @Valid CreateCategoryDTO data, UriComponentsBuilder uriBuilder) {
        var category = new Category(data);
        repository.save(category);

        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getIdcategory()).toUri();

        return ResponseEntity.created(uri).body(new ListCategoriesDTO(category));
    }

    @GetMapping
    public ResponseEntity<Page<ListCategoriesDTO>> list(Pageable pageable) {
        var page = repository.findAllByIscategoryactiveTrue(pageable).map(ListCategoriesDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListCategoriesDTO> update(@RequestBody @Valid UpdateCategoryDTO data) {
        var category = repository.getReferenceById(data.idcategory());
        category.updateData(data);

        return ResponseEntity.ok(new ListCategoriesDTO(category));
    }

    @DeleteMapping("/{idcategory}")
    @Transactional
    public ResponseEntity delete(@PathVariable long idcategory) {
        var category = repository.getReferenceById(idcategory);
        category.delete();

        return ResponseEntity.noContent().build();
    }

}

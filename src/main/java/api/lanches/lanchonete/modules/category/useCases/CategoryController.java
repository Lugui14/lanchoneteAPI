package api.lanches.lanchonete.modules.category.useCases;

import api.lanches.lanchonete.modules.category.dtos.CreateCategoryDTO;
import api.lanches.lanchonete.modules.category.dtos.ListCategoriesDTO;
import api.lanches.lanchonete.modules.category.dtos.UpdateCategoryDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class CategoryController {

    @Autowired
    private CategoryUseCase categoryUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<ListCategoriesDTO> create(@RequestBody @Valid CreateCategoryDTO data, UriComponentsBuilder uriBuilder) {
        var category = categoryUseCase.create(data);
        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getIdcategory()).toUri();
        return ResponseEntity.created(uri).body(new ListCategoriesDTO(category));
    }

    @GetMapping
    public ResponseEntity<Page<ListCategoriesDTO>> list(Pageable pageable) {
        var page = categoryUseCase.list(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListCategoriesDTO> update(@RequestBody @Valid UpdateCategoryDTO data) {
        var category = categoryUseCase.update(data);
        return ResponseEntity.ok(new ListCategoriesDTO(category));
    }

    @DeleteMapping("/{idcategory}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable long idcategory) {
        categoryUseCase.delete(idcategory);
        return ResponseEntity.noContent().build();
    }

}

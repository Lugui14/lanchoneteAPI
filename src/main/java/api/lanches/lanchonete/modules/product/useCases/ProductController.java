package api.lanches.lanchonete.modules.product.useCases;

import api.lanches.lanchonete.modules.product.dtos.CreateProductDTO;
import api.lanches.lanchonete.modules.product.dtos.ListProductDTO;
import api.lanches.lanchonete.modules.product.dtos.ProductDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductUseCase productUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<ListProductDTO> create(@RequestBody @Valid CreateProductDTO data, UriComponentsBuilder uriBuilder) {
        var product = productUseCase.create(data);
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.idproduct()).toUri();

        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<ListProductDTO>> list(Pageable pageable) {
        var page = productUseCase.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idproduct}")
    public ResponseEntity<ListProductDTO> getOne(@PathVariable long idproduct) {
        var product = productUseCase.getOne(idproduct);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListProductDTO> update(@RequestBody @Valid ProductDTO data) {
        var product = productUseCase.update(data);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{idproduct}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long idproduct) {
        productUseCase.delete(idproduct);
        return ResponseEntity.ok().build();
    }

}

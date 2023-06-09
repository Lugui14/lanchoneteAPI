package api.lanches.lanchonete.modules.waiter.useCases;

import api.lanches.lanchonete.modules.waiter.dtos.CreateWaiterDTO;
import api.lanches.lanchonete.modules.waiter.dtos.ListWaiterDTO;
import api.lanches.lanchonete.modules.waiter.dtos.UpdateWaiterDTO;
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
@RequestMapping("/waiter")
@SecurityRequirement(name = "bearer-key")
public class WaiterController {

    @Autowired
    private WaiterUseCase waiterUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<ListWaiterDTO> create(@RequestBody @Valid CreateWaiterDTO data, UriComponentsBuilder uriBuilder) {

        var waiter = waiterUseCase.create(data);
        var uri = uriBuilder.path("/waiter/{id}").buildAndExpand(waiter.idwaiter()).toUri();
        return ResponseEntity.created(uri).body(waiter);
    }

    @GetMapping
    public ResponseEntity<Page<ListWaiterDTO>> list(Pageable pageable) {
        var page = waiterUseCase.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idwaiter}")
    public ResponseEntity<ListWaiterDTO> getOne(@PathVariable Long idwaiter) {
        var waiter = waiterUseCase.getOne(idwaiter);
        return ResponseEntity.ok(waiter);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListWaiterDTO> update(@RequestBody @Valid UpdateWaiterDTO data) {

        var waiter = waiterUseCase.update(data);
        return ResponseEntity.ok(waiter);
    }

    @DeleteMapping("/{idwaiter}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable long idwaiter) {

        waiterUseCase.delete(idwaiter);
        return ResponseEntity.ok().build();
    }
}

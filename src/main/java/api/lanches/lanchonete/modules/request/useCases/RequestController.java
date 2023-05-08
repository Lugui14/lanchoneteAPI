package api.lanches.lanchonete.modules.request.useCases;

import api.lanches.lanchonete.modules.request.dtos.CreateRequestDTO;
import api.lanches.lanchonete.modules.request.dtos.ListRequestDTO;
import api.lanches.lanchonete.modules.request.dtos.UpdateRequestDTO;
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
@RequestMapping("/request")
@SecurityRequirement(name = "bearer-key")
public class RequestController {

    @Autowired
    private RequestUseCase requestUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<ListRequestDTO> create(@RequestBody @Valid CreateRequestDTO data, UriComponentsBuilder uriBuilder) {
        var request = requestUseCase.create(data);
        var uri = uriBuilder.path("/request/{idrequest}").buildAndExpand(request.idrequest()).toUri();

        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping("/{idcontrol}")
    public ResponseEntity<Page<ListRequestDTO>> list(@PathVariable Long idcontrol, Pageable pageable) {
        var page = requestUseCase.list(idcontrol, pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListRequestDTO> update(@RequestBody @Valid UpdateRequestDTO data) {
        var request = requestUseCase.update(data);

        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{idrequest}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long idrequest) {
        requestUseCase.delete(idrequest);

        return ResponseEntity.ok().build();
    }
}

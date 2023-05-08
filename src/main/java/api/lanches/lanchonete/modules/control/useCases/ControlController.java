package api.lanches.lanchonete.modules.control.useCases;

import api.lanches.lanchonete.modules.control.dtos.CreateControlDTO;
import api.lanches.lanchonete.modules.control.dtos.GetOneControlDTO;
import api.lanches.lanchonete.modules.control.dtos.ListControlDTO;
import api.lanches.lanchonete.modules.control.dtos.UpdateControlDTO;
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
@RequestMapping("/control")
@SecurityRequirement(name = "bearer-key")
public class ControlController {

    @Autowired
    private ControlUseCase controlUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity<GetOneControlDTO> create(@RequestBody @Valid CreateControlDTO data, UriComponentsBuilder uriBuilder) {
        var control = controlUseCase.create(data);
        var uri = uriBuilder.path("/control/{idcontrol}").buildAndExpand(control.idcontrol()).toUri();

        return ResponseEntity.created(uri).body(control);
    }

    @GetMapping("/opened={opened}")
    public ResponseEntity<Page<ListControlDTO>> list(@PathVariable boolean opened, Pageable pageable) {
        var page = controlUseCase.list(opened, pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idcontrol}")
    public ResponseEntity<GetOneControlDTO> getOne(@PathVariable Long idcontrol) {
        var control = controlUseCase.getOne(idcontrol);

        return ResponseEntity.ok(control);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetOneControlDTO> update(@RequestBody @Valid UpdateControlDTO data) {
        var control = controlUseCase.update(data);

        return ResponseEntity.ok(control);
    }

    @DeleteMapping("/{idcontrol}")
    @Transactional
    public ResponseEntity<Object> closeControl(@PathVariable Long idcontrol) {
        controlUseCase.closeControl(idcontrol);

        return ResponseEntity.ok().build();
    }

}

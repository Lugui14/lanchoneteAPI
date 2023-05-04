package api.lanches.lanchonete.modules.request.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateRequestDTO(
        @NotNull
        Long idproduct,
        @NotNull
        Long idcontrol) {
}

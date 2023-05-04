package api.lanches.lanchonete.modules.request.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateRequestDTO(@NotNull Long idrequest, Long idproduct) {
}

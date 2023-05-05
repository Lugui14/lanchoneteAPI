package api.lanches.lanchonete.modules.control.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateControlDTO(@NotBlank String client,
                               @NotNull int controlnumber,
                               @NotNull Long idwaiter) {
}

package api.lanches.lanchonete.modules.waiter.dtos;

import api.lanches.lanchonete.modules.waiter.infra.Waiter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWaiterDTO(
        @NotBlank
        String waiter,
        @NotNull
        double salary
) {
}

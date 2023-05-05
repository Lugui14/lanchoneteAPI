package api.lanches.lanchonete.modules.control.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateControlDTO(@NotNull Long idcontrol,
                               String client,
                               @NotNull double discount,
                               int controlnumber,
                               Long idwaiter) {
}

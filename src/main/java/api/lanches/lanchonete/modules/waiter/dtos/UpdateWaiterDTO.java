package api.lanches.lanchonete.modules.waiter.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateWaiterDTO(@NotNull Long idwaiter, String waiter, double salary) {
}

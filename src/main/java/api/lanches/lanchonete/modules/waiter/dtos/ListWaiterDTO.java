package api.lanches.lanchonete.modules.waiter.dtos;

import api.lanches.lanchonete.modules.waiter.infra.Waiter;

public record ListWaiterDTO(Long idwaiter, String waiter, double salary) {
    public ListWaiterDTO(Waiter data) {
        this(data.getIdwaiter(), data.getWaiter(), data.getSalary());
    }
}

package api.lanches.lanchonete.modules.control.dtos;

import api.lanches.lanchonete.modules.waiter.infra.Waiter;

public record DetailControlDTO(String client,
                               double discount,
                               int controlnumber,
                               Waiter waiter) {
}

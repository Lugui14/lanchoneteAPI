package api.lanches.lanchonete.modules.control.dtos;

import api.lanches.lanchonete.modules.control.infra.Control;

import java.time.LocalDateTime;

public record GetOneControlDTO(Long idcontrol,
                               String client,
                               double topay,
                               double discount,
                               boolean isclosed,
                               int controlnumber,
                               LocalDateTime entrace,
                               String waiter) {

    public GetOneControlDTO(Control control) {
        this(control.getIdcontrol(),
                control.getClient(),
                control.getTopay(),
                control.getDiscount(),
                control.isIsclosed(),
                control.getControlnumber(),
                control.getEntrace(),
                control.getWaiter().getWaiter());
    }
}

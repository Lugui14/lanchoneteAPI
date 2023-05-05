package api.lanches.lanchonete.modules.control.dtos;

import api.lanches.lanchonete.modules.control.infra.Control;

public record ListControlDTO(Long idcontrol,
                             String client,
                             String waiter,
                             int controlnumber,
                             boolean isclosed) {

    public ListControlDTO(Control control) {
        this(control.getIdcontrol(),
                control.getClient(),
                control.getWaiter().getWaiter(),
                control.getControlnumber(),
                control.isIsclosed());
    }
}

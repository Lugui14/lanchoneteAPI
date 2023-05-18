package api.lanches.lanchonete.modules.request.dtos;

import api.lanches.lanchonete.modules.request.infra.Request;

public record ListRequestDTO(Long idrequest, int controlnumber, String product, double vlvenda, int requeststatus) {

    public ListRequestDTO(Request request) {
        this(request.getIdrequest(), request.getControl().getControlnumber(), request.getProduct().getProduct(), request.getVlvenda(), request.getRequeststatus());
    }

}

package api.lanches.lanchonete.modules.request.dtos;

import api.lanches.lanchonete.modules.request.infra.Request;

public record ListRequestDTO(Long idrequest, Long idcontrol, String product, double vlvenda) {

    public ListRequestDTO(Request request) {
        this(request.getIdrequest(), request.getControl().getIdcontrol(), request.getProduct().getProduct(), request.getVlvenda());
    }

}

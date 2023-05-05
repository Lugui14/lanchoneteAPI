package api.lanches.lanchonete.modules.control.dtos;

public record ControlDTO(String client,
                         double topay,
                         double discount,
                         int controlnumber,
                         Long idwaiter) {
}

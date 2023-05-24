package api.lanches.lanchonete.modules.control.useCases;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.dtos.*;
import api.lanches.lanchonete.modules.control.infra.Control;
import api.lanches.lanchonete.modules.control.infra.ControlRepository;
import api.lanches.lanchonete.modules.control.useCases.validation.ControlValidation;
import api.lanches.lanchonete.modules.request.dtos.DetailRequestDTO;
import api.lanches.lanchonete.modules.request.infra.RequestRepository;
import api.lanches.lanchonete.modules.waiter.infra.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControlUseCase {

    @Autowired
    private List<ControlValidation> validation;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private ControlRepository controlRepository;

    @Autowired
    private RequestRepository requestRepository;

    public GetOneControlDTO create(CreateControlDTO data) {

        validation.forEach(v -> v.validate(new ControlDTO(
                data.client(),
                0,
                0,
                data.controlnumber(),
                data.idwaiter()
        )));

        var waiter = waiterRepository.getReferenceById(data.idwaiter());
        var control = new Control(data.client(), data.controlnumber(), waiter);

        controlRepository.save(control);

        return new GetOneControlDTO(control);
    }

    public ArrayList<Integer> listAvailableNumbers() {
        ArrayList<Integer> AvailableNumbers = new ArrayList<>();
        for(int i = 1; i <= 20; i++) {
            AvailableNumbers.add(i);
        }

        ArrayList<Integer> UnavailableNumbers = controlRepository.findUnavailableNumbers();
        for(Integer number: UnavailableNumbers) {
            AvailableNumbers.remove(number);
        }

        return AvailableNumbers;
    }

    public Page<ListControlDTO> list(boolean opened, Pageable pageable) {
        return opened ?
         controlRepository.findAllByIsclosed(false, pageable).map(ListControlDTO::new)
        :  controlRepository.findAllByIsclosed(true, pageable).map(ListControlDTO::new);
    }

    public GetOneControlDTO getOne(Long idcontrol) {

        if(!controlRepository.existsById(idcontrol)) {
            throw new ValidationException("A Comanda especificada não existe");
        }

        var control = controlRepository.getReferenceById(idcontrol);

        return new GetOneControlDTO(control);
    }

    public GetOneControlDTO update(UpdateControlDTO data) {
        validation.forEach(v -> v.validate(new ControlDTO(
                data.client(),
                0,
                data.discount(),
                data.controlnumber(),
                data.idwaiter()
        )));

        var waiter = waiterRepository.getReferenceById(data.idwaiter());
        var control = controlRepository.getReferenceById(data.idcontrol());

        control.update(new DetailControlDTO(data.client(), data.discount(), data.controlnumber(), waiter));

        return new GetOneControlDTO(control);
    }

    public void closeControl(Long idcontrol, Pageable pageable) {
        if(!controlRepository.existsByIdcontrolAndIsclosedFalse(idcontrol)) {
            throw new ValidationException("A comanda especificada não existe ou já está fechada");
        }

        
        
        var control = controlRepository.getReferenceById(idcontrol);
        var requests = requestRepository.findAllByControl(control, pageable).getContent();

        if(control.getTopay() > 0) {
            throw new ValidationException("Você não pode fechar uma comanda que ainda tem pagamentos pendentes");
        }

        requests.forEach(request -> requestRepository.getReferenceById(request.getIdrequest()).update(new DetailRequestDTO(
                null,
                3
        )));

        control.delete();
    }
}

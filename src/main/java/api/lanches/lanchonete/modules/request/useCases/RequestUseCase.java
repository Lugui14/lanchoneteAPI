package api.lanches.lanchonete.modules.request.useCases;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.control.infra.ControlRepository;
import api.lanches.lanchonete.modules.product.infra.ProductRepository;
import api.lanches.lanchonete.modules.request.dtos.*;
import api.lanches.lanchonete.modules.request.infra.Request;
import api.lanches.lanchonete.modules.request.infra.RequestRepository;
import api.lanches.lanchonete.modules.request.useCases.validation.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ControlRepository controlRepository;

    @Autowired
    private List<RequestValidation> validations;
    public ListRequestDTO create(CreateRequestDTO data) {

        validations.forEach(v -> v.validate(new RequestDTO(null, data.idproduct(), data.idcontrol())));

        var product = productRepository.getReferenceById(data.idproduct());
        var control = controlRepository.getReferenceById(data.idcontrol());
        var request = new Request(null, 0, product.getPrice(), product, control);

        requestRepository.save(request);

        return new ListRequestDTO(request);
    }

    public Page<ListRequestDTO> listLasts(Pageable pageable) {
        return requestRepository.findAll(pageable).map(ListRequestDTO::new);
    }

    public Page<ListRequestDTO> list(Long idcontrol, Pageable pageable) {

        //validacao individual
        if(!controlRepository.existsById(idcontrol)) {
            throw new ValidationException("A comanda especificada nao exsite");
        }

        var control = controlRepository.getReferenceById(idcontrol);

        return requestRepository.findAllByControl(control, pageable).map(ListRequestDTO::new);
    }

    public ListRequestDTO update(UpdateRequestDTO data) {

        //validacao individual
        if(!requestRepository.existsById(data.idrequest())) {
            throw new ValidationException("Pedido inexistente.");
        }

        var request = requestRepository.getReferenceById(data.idrequest());
        validations.forEach(v -> v.validate(new RequestDTO(data.idrequest(),
                data.idproduct(),
                request.getControl().getIdcontrol()
        )));

        var product = productRepository.getReferenceById(data.idproduct());

        request.update(new DetailRequestDTO(product, data.requeststatus()));

        return new ListRequestDTO(request);
    }

    public void delete(Long idrequest) {

        //validacao individual
        if(!requestRepository.existsById(idrequest)) {
            throw new ValidationException("Pedido inexistente.");
        }

        requestRepository.deleteById(idrequest);
    }

}

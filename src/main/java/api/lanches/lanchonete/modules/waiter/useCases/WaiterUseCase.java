package api.lanches.lanchonete.modules.waiter.useCases;

import api.lanches.lanchonete.infra.exceptions.ValidationException;
import api.lanches.lanchonete.modules.waiter.dtos.CreateWaiterDTO;
import api.lanches.lanchonete.modules.waiter.dtos.ListWaiterDTO;
import api.lanches.lanchonete.modules.waiter.dtos.UpdateWaiterDTO;
import api.lanches.lanchonete.modules.waiter.infra.Waiter;
import api.lanches.lanchonete.modules.waiter.infra.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WaiterUseCase {

    @Autowired
    private WaiterRepository repository;

    public Waiter create(CreateWaiterDTO data) {
        if(data.salary() < 1300) {
            throw new ValidationException("O salario não pode ser menor que 1300 (salario minimo).");
        }

        var waiter = new Waiter(data);
        repository.save(waiter);

        return waiter;
    }

    public Page<ListWaiterDTO> list(Pageable pageable) {
        return repository.findAll(pageable).map(ListWaiterDTO::new);
    }

    public Waiter getOne(long idwaiter) {
        return repository.getReferenceById(idwaiter);
    }

    public Waiter update(UpdateWaiterDTO data) {
        if(data.salary() != 0 && data.salary() < 1300) {
            throw new ValidationException("O salario não pode ser menor que 1300 (salario minimo).");
        }

        var waiter = repository.getReferenceById(data.idwaiter());
        waiter.updateWaiter(data);

        return waiter;
    }

    public void delete(long idwaiter) {
        repository.deleteById(idwaiter);
    }
}

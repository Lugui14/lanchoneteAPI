package api.lanches.lanchonete.modules.request.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Page<Request> findAllByControl(Control control, Pageable pageable);

}

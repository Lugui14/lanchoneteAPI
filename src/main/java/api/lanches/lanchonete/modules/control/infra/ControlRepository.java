package api.lanches.lanchonete.modules.control.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlRepository extends JpaRepository<Control, Long> {

    boolean existsControlByIdcontrolAndIsclosedFalse(Long idcontrol);

    boolean existsAllByControlnumber(int controlnumber);

    Page<Control> findAllByIsclosed(boolean isclosed, Pageable pageable);

}

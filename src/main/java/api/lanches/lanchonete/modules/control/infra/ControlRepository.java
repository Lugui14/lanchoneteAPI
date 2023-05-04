package api.lanches.lanchonete.modules.control.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlRepository extends JpaRepository<Control, Long> {

    boolean existsControlByIdcontrolAndIsclosedFalse(Long idcontrol);

}

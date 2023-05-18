package api.lanches.lanchonete.modules.control.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ControlRepository extends JpaRepository<Control, Long> {

    boolean existsByIdcontrolAndIsclosedFalse(Long idcontrol);

    @Query(value = "select controlnumber from control where isclosed = false",
    nativeQuery = true)
    ArrayList<Integer> findUnavailableNumbers();

    boolean existsAllByControlnumberAndIsclosedFalse(int controlnumber);

    Page<Control> findAllByIsclosed(boolean isclosed, Pageable pageable);

}

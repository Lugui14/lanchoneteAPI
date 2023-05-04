package api.lanches.lanchonete.modules.waiter.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
import api.lanches.lanchonete.modules.waiter.dtos.CreateWaiterDTO;
import api.lanches.lanchonete.modules.waiter.dtos.UpdateWaiterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "waiter")
@Entity(name = "Waiter")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idwaiter")
public class Waiter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idwaiter;
    private String waiter;
    private double salary;

    @OneToMany(mappedBy = "waiter")
    private List<Control> control;

    public Waiter(CreateWaiterDTO data) {
        this.waiter = data.waiter();
        this.salary = data.salary();
    }

    public void updateWaiter(UpdateWaiterDTO data) {
        if(data.waiter() != null) {
            this.waiter = data.waiter();
        }

        if(data.salary() != 0) {
            this.salary = data.salary();
        }
    }

}

package api.lanches.lanchonete.modules.waiter.infra;

import api.lanches.lanchonete.modules.control.infra.Control;
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

}

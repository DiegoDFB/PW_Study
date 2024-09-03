//Arthur de Jesus Lima e Diego Francischette Blanco

package etechoracio.pw_study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TBL_DISPONIBILIDADE")
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISPONIBILIDADE")
    private Long idDisponibilidade;

    @Column(name = "TX_DIA_SEMANA")
    private String diaSemana;

    @Column(name = "DT_DAS")
    private Date das;

    @Column(name = "DT_ATE")
    private Date ate;

    @ManyToMany(mappedBy = "disponibilidades")
    private List<Monitor> monitores;

}

//Arthur de Jesus Lima e Diego Francischette Blanco

package etechoracio.pw_study.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TBL_DISCIPLINA")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISCIPLINA")
    private Long idDisciplina;

    @Column(name = "TX_NOME")
    private String nome;

}

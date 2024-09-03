//Arthur de Jesus Lima e Diego Francischette Blanco

package etechoracio.pw_study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "TBL_CONEXAO")
public class Conexao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONEXAO")
    private Long idConexao;

    @Column(name = "DT_CRIACAO")
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "ID_MONITOR")
    private Monitor monitor;

}
package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Professor extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_controle")
    private Date data;

    @Column(name = "regime_trabalho")
    private String regimeTrabalho;

    /*Aqui informa a área como Direito, Economia, Administração, etc.*/
    @Column(name = "area", length = 100)
    private String area;

    @Column(name = "descricao_funcao", length = 100)
    private String descricaoFuncao;

    @Column(name = "tem_funcao")
    private boolean temFuncao;
    
    @Column(name = "dedicacao_exclusiva")
    private boolean dedicacaoExclusiva;
    
    @Column(name = "classe", length = 50)
    private String classe;
    
    @Column(name = "nivel", length = 50)
    private String nivel;

    @CPF
    @Column(name = "cpf", unique = true)
    private String cpf;

//    @ManyToMany(targetEntity = Pendencia.class, mappedBy = "professores")
//    private List<Pendencia> pendencia = new ArrayList<>();
//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "professor_pendencia", referencedColumnName = "id")
//    private Pendencia pendencia;
}

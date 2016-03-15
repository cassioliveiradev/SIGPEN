package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.StatusPendencia;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Entity
@Data
public class Pendencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name = "remetente", length = 255)
    private String remetente;

    @Column(name = "destinatario", length = 50)
    private String destinatario;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_recebimento")
    private Date dataRecebimentoSecretaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "localizacao_fisica")
    private LocalizacaoFisicaUAEDUC localizacaoFisica;

    @Column(name = "outra_localizacao", length = 200)
    private String outraLocalizacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrega_destinatario")
    private Date dataEntregaDestinatario;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_controle")
    private Date data;

    @Lob
    @Column(name = "observacoes")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPendencia status;

    @Column(name = "nome_usuario")
    private String nomeUsuario;
    
    @Column(name = "motivo_baixa")
    private String motivoBaixa;

    @Column(name = "professor")
    private String professor;

//    @ManyToMany(targetEntity = Professor.class, cascade = CascadeType.ALL)
//    @JoinTable(name = "professor")
//    private List<Professor> professores = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        setStatus(StatusPendencia.ABERTA);
    }
}

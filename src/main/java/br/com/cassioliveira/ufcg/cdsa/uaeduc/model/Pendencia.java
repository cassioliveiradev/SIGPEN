package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Entity
@Data
@NamedQuery(name = "Pendencia.professores", query = "SELECT p.professores FROM Pendencia p")
public class Pendencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull(message = "Informe a descrição da pendência")
    @Column(name = "pendencia_descricao", nullable = false)
    private String descricao;

    @Column(name = "pendencia_remetente", length = 255)
    private String remetente;

    @Column(name = "pendencia_destinatario", length = 50)
    private String destinatario;

    @Temporal(TemporalType.DATE)
    @Column(name = "pendencia_data_recebimento")
    private Date dataRecebimentoSecretaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "pendencia_localizacao_fisica")
    private LocalizacaoFisicaUAEDUC localizacaoFisica;

    @Column(name = "pendencia_outra_localizacao", length = 200)
    private String outraLocalizacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "pendencia_entrega_destinatario")
    private Date dataEntregaDestinatario;

    @Temporal(TemporalType.DATE)
    @Column(name = "pendencia_data")
    private Date data;

    @Lob
    @Column(name = "pendencia_observacoes")
    private String observacoes;

    @Column(name = "pendencia_status")
    private String status;

    @Column(name = "pendencia_nome_usuario")
    private String nomeUsuario;

    @NotNull(message = "É necessário informar um ou mais professores para essa pendência")
    @Column(name = "pendencia_professores")
    private List<String> professores;

//    @ManyToMany(targetEntity = Professor.class, cascade = CascadeType.ALL)
//    @JoinTable(name = "pendencia_professor")
//    private List<Professor> professores = new ArrayList<>();
}

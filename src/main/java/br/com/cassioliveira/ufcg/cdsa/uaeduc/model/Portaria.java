package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Data
@Entity
public class Portaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Professor professor;

    @Column(name = "descricao", nullable = false, length = 250)
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_publicacao")
    private Date dataPublicacao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "validade_portaria")
    private Date validadePortaria;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    private byte[] pdfDaPortaria;

    @Lob
    @Column(name = "observacoes")
    private String observacoes;
}

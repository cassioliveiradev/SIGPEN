package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class PendenciaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @Getter
    @Setter
    private Pendencia pendencia;

    @Inject
    @Getter
    @Setter
    private Pendencia pendenciaSelecionada;

    @Inject
    @Getter
    @Setter
    private PendenciaService pendenciaService;

    @Getter
    @Setter
    private Professor professor;

    @Getter
    private List<Pendencia> pendencias;

    @Getter
    private transient List<LocalizacaoFisicaUAEDUC> localizacoesFisicas;

    public PendenciaBean() {

    }

    public void novoProfessor() {
        this.professor = new Professor();
    }

    @PostConstruct
    public void init() {
        this.localizacoesFisicas = Arrays.asList(LocalizacaoFisicaUAEDUC.values());
        this.pendencias = pendenciaService.findAll();
    }

//    public void associarProfessor() {
//        pendencia.getProfessores().add(professor);
//        professor.setPendencia(pendencia);
//    }

    public void salvar() {
        this.pendenciaService.salvar(pendencia);
//        professor.setPendencia(pendencia);
        if (getEditando()) {
            FacesUtil.mensagemSucesso("Pendência atualizada com sucesso!");
        } else {
            FacesUtil.mensagemSucesso("Pendência cadastrada com sucesso!");
        }
        FacesUtil.redirecionaPara("PesquisaPendencia.xhtml");
        pendencia = new Pendencia();
    }

    public void delete() {
        this.pendenciaService.delete(pendencia);
        FacesUtil.mensagemSucesso("Pendência excluida com sucesso!");
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     *
     * @return
     */
    public boolean getEditando() {
        return this.pendencia.getId() != null;
    }

}

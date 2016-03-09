package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.ProfessorService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class PendenciaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(PendenciaBean.class);

    @Inject
    @Getter
    @Setter
    private Pendencia pendencia;

    @Getter
    @Setter
    private Pendencia pendenciaSelecionada;

    @Inject
    @Getter
    @Setter
    private PendenciaService pendenciaService;

    @Inject
    private ProfessorService professorService;

    @Inject
    @Getter
    @Setter
    private Professor professor;

    @Getter
    private List<Pendencia> pendencias;

    @Getter
    private transient List<LocalizacaoFisicaUAEDUC> localizacoesFisicas;

    public PendenciaBean() {

    }

    @PostConstruct
    public void init() {
        this.localizacoesFisicas = Arrays.asList(LocalizacaoFisicaUAEDUC.values());
        this.pendencias = pendenciaService.findAll();
    }

    public void salvar() {
        pendenciaService.salvar(pendencia);

        if (getEditando()) {
            FacesUtil.mensagemSucesso("Pendência atualizada com sucesso!");
        } else {
            FacesUtil.mensagemSucesso("Pendência cadastrada com sucesso!");
        }
        FacesUtil.redirecionaPara("PesquisaPendencia.xhtml");
        pendencia = new Pendencia();
    }

    public void delete() {
        this.pendenciaService.delete(pendenciaSelecionada);
        FacesUtil.redirecionaPara("PesquisaPendencia.xhtml");
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

    public List<Professor> getProfessores() {
        return professorService.findAll();
    }

    /**
     * Retorna a lista de pendências com as mesmas replicadas em quantidade de
     * acordo com a quantidade de professores na pendência.
     *
     * @return
     */
    public List<Pendencia> getReplicarPendenciasPorProfessores() {
        List<Pendencia> pendenciasPorProfessor = new ArrayList<>();
        for (Pendencia pendenciaListada : pendencias) {
            for (int i = 0; i < pendenciaListada.getProfessores().size(); i++) {
                pendenciasPorProfessor.add(pendenciaListada);
            }
        }
        return pendenciasPorProfessor;
    }

//    public List<String> getListaProfessoresComPendencia() {
//        List<String> professoresComPendencia = new ArrayList<>();
//
//        for (Pendencia pendenciaListada : pendencias) {
//            for (int i = 0; i < pendenciaListada.getProfessores().size(); i++) {
//                String professorDaVez = pendenciaListada.getProfessores().get(i);
//                System.out.println("@@@@@@@@@@@@@@@@@@@ Professor da vez: " + professorDaVez);
//                professoresComPendencia.add(professorDaVez);
//            }
//        }
//        return professoresComPendencia;
//    }
}

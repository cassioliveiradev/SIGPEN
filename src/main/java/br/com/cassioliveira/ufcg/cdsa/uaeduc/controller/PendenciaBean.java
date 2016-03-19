package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.StatusPendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
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
    @Getter
    @Setter
    private Professor professor;

    @Getter
    private List<Pendencia> pendencias;

    @Getter
    @Setter
    private List<String> professores;

    @Getter
    private transient List<LocalizacaoFisicaUAEDUC> localizacoesFisicas;

    DateTimeUtilBean dateTime;

    public PendenciaBean() {
        dateTime = new DateTimeUtilBean();
    }

    @PostConstruct
    public void init() {
        this.localizacoesFisicas = Arrays.asList(LocalizacaoFisicaUAEDUC.values());
        this.pendencias = pendenciaService.findAll();
    }

    public void salvar() {
        for (int i = 0; i < getProfessores().size(); i++) {
            String professorSelecionado = getProfessores().get(i);
            pendencia.setProfessor(professorSelecionado);
            pendenciaService.salvar(pendencia);
        }
        FacesUtil.mensagemSucesso("Pendência cadastrada com sucesso!");
        FacesUtil.redirecionaPara("pesquisa-pendencia.xhtml");
        pendencia = new Pendencia();
    }

    public void editar() {
        pendenciaService.editar(pendencia);
        FacesUtil.mensagemSucesso("Pendência atualizada com sucesso!");
        FacesUtil.redirecionaPara("pesquisa-pendencia.xhtml");
    }

    public void darBaixa() {
        pendencia.setStatus(StatusPendencia.FECHADA);
        pendencia.setDataEntregaDestinatario(dateTime.getDateToday());
        pendenciaService.editar(pendencia);
        FacesUtil.mensagemSucesso("Baixa de pendência realizada com sucesso!");
        FacesUtil.redirecionaPara("pesquisa-pendencia.xhtml");
    }

    public void delete() {
        this.pendenciaService.delete(pendenciaSelecionada);
        FacesUtil.redirecionaPara("pendencias-fechadas.xhtml");
        FacesUtil.mensagemSucesso("Pendência excluida com sucesso!");
    }

    /**
     * Verifica e retorna todas as pendências cadastradas e em aberto, desde que
     * o status das mesmas esteja como 'Aberta'.
     *
     * @see StatusPendencia
     *
     * @return
     */
    public List<Pendencia> getPendenciasAbertas() {
        List<Pendencia> pendenciasAbertas = new ArrayList<>();

        for (Pendencia pendenciaAberta : pendencias) {
            if (pendenciaAberta.getStatus() == StatusPendencia.ABERTA) {
                pendenciasAbertas.add(pendenciaAberta);
            }
        }

        return pendenciasAbertas;
    }

    /**
     * Verifica e retorna todas as pendências cadastradas e finalizadas, desde
     * que o status das mesmas esteja como 'Fechada'.
     *
     * @see StatusPendencia
     *
     * @return
     */
    public List<Pendencia> getPendenciasFechadas() {
        List<Pendencia> pendenciasFechadas = new ArrayList<>();

        for (Pendencia pendenciaFechada : pendencias) {
            if (pendenciaFechada.getStatus() == StatusPendencia.FECHADA) {
                pendenciasFechadas.add(pendenciaFechada);
            }
        }

        return pendenciasFechadas;
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

package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.StatusPendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Named
@ViewScoped
public class PendenciaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(PendenciaBean.class);

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

    @Getter
    private List<Pendencia> pendencias;

    @Getter
    @Setter
    private List<String> professores;

    @Getter
    private transient List<LocalizacaoFisicaUAEDUC> localizacoesFisicas;

    DateTimeUtilBean dateTime;

    public PendenciaBean() {
        this.pendencia = new Pendencia();
        this.pendenciaSelecionada = new Pendencia();
        dateTime = new DateTimeUtilBean();
    }

    @PostConstruct
    public void init() {
        this.localizacoesFisicas = Arrays.asList(LocalizacaoFisicaUAEDUC.values());
        this.pendencias = pendenciaService.todas();
    }

    public void salvar() {
        for (int nomeAtual = 0; nomeAtual < getProfessores().size(); nomeAtual++) {
            String professorSelecionado = getProfessores().get(nomeAtual);
            pendencia.setProfessor(professorSelecionado);
            pendencia.setId(null);
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

    public void excluir() {
        this.pendenciaService.excluir(pendenciaSelecionada);
        FacesUtil.redirecionaPara("pendencias-fechadas.xhtml");
        FacesUtil.mensagemSucesso("Pendência excluida com sucesso!");
    }

    /**
     * Retorna todas as pendências com status "ABERTA"
     *
     * @see StatusPendencia
     *
     * @return
     */
    public List<Pendencia> getPendenciasAbertas() {
        return pendenciaService.pendencias(StatusPendencia.ABERTA);
    }

    /**
     * Retorna todas as pendências com status "FECHADA"
     *
     * @see StatusPendencia
     *
     * @return
     */
    public List<Pendencia> getPendenciasFechadas() {
        return pendenciaService.pendencias(StatusPendencia.FECHADA);
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

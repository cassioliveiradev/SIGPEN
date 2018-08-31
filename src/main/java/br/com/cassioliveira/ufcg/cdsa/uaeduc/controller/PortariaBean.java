package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.LocalizacaoFisicaUAEDUC;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Portaria;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PortariaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.Serializable;
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
public class PortariaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(PortariaBean.class);

    @Getter
    @Setter
    private Portaria portaria;

    @Getter
    @Setter
    private Portaria portariaSelecionada;

    @Inject
    @Getter
    @Setter
    private PortariaService portariaService;

    @Getter
    private List<Portaria> portarias;

    @Getter
    private transient List<LocalizacaoFisicaUAEDUC> localizacoesFisicas;

    DateTimeUtilBean dateTime;

    public PortariaBean() {
        this.portaria = new Portaria();
        this.portariaSelecionada = new Portaria();
        dateTime = new DateTimeUtilBean();
    }

    @PostConstruct
    public void init() {
        this.localizacoesFisicas = Arrays.asList(LocalizacaoFisicaUAEDUC.values());
        this.portarias = portariaService.todas();
    }

    public void salvar() {
        portariaService.salvar(portaria);
        FacesUtil.mensagemSucesso("Portaria cadastrada com sucesso!");
        FacesUtil.redirecionaPara("cadastro-portaria.xhtml");
        portaria = new Portaria();
    }

    public void editar() {
        portariaService.editar(portaria);
        FacesUtil.mensagemSucesso("Portaria atualizada com sucesso!");
        FacesUtil.redirecionaPara("cadastro-portaria.xhtml");
    }

    public void excluir() {
        this.portariaService.excluir(portariaSelecionada);
        FacesUtil.mensagemSucesso("Portaria excluida com sucesso!");
        FacesUtil.redirecionaPara("cadastro-portaria.xhtml");
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     *
     * @return
     */
    public boolean getEditando() {
        return this.portaria.getId() != null;
    }
}

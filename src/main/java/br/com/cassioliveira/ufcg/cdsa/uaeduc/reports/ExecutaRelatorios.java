package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.controller.PendenciaBean;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.controller.ProfessorBean;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;

/**
 * Emite o relatório baseado em métodos presentes na classe
 * <b>GeraRelatorios</b> e que fazem a conexão com o banco de dados e tratam os
 * dados para gerar os relatórios. Ao chamar essa classe utilitária de geração
 * de relatórios, dois parâmetros são passados, onde o primeiro refere-se ao
 * nome do arquivo de template de relatório compilado (.jasper) e o segundo ao
 * nome do relatório em PDF que o usuário visualizará. Cada método dessa classe
 * representa a chamada a um relatorio diferente
 *
 * @see GeraRelatorios
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class ExecutaRelatorios implements Serializable {
    
//    @Inject
//    private FacesContext facesContext;
//
//    @Inject
//    private HttpServletResponse response;
//
//    @Inject
//    private EntityManager entityManager;
//
//    /**
//     * Emite um relatório com todos as pendências em que o status esteja 'ABERTO'
//     */
//    public void emitirRelatorioPendenciasAbertas() {
//        Map<String, Object> parametros = new HashMap<>();
//        String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ufcg-central-200.png");
//        parametros.put("logo", logo);
//
//        GeraRelatorios geraRelatorios = new GeraRelatorios("/relatorio_pendencias_abertas.jasper",
//                this.response, parametros, "Pendências abertas.pdf");
//
//        Session session = entityManager.unwrap(Session.class);
//        session.doWork(geraRelatorios);
//
//        facesContext.responseComplete();
//    }
    @Inject
    private GeraRelatorios geradorRelatorios;
    
    @Inject
    @Getter
    @Setter
    private PendenciaService pendenciaService;
    
    @Inject
    @Getter
    @Setter
    private PendenciaBean pendenciaBean;
    
    @Inject
    @Getter
    @Setter
    private ProfessorBean professorBean;
    
    /**
     * Emite um relatório com todos os pedidos emitidos em que o status esteja
     * 'ABERTO'
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
//    public void emitirRelatorioPendenciasAbertas() throws SQLException, JRException, IOException {
//        geradorRelatorios.gerarPdfDownload("/relatorio_pendencias_abertas.jasper", "Relatorio de pendencias abertas.pdf", pendenciaBean.getPendenciasAbertas());
//    }

//    /**
//     * Emite um relatório com todos os pedidos emitidos em que o status esteja
//     * 'FECHADO'
//     *
//     * @throws java.sql.SQLException
//     */
//    public void emitirRelatorioPendenciasFechadas() throws SQLException {
//        geradorRelatorios.preparaRelatorioPdf("/relatorio_pendencias_fechadas.jasper", "Relatorio de pendencias fechadas.pdf");
//    }
//
    /**
     * Emite um relatório com todos os professores cadastrados.
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void emitirRelatorioTodosProfessores() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdfDownload("/relatorio_professores_cadastrados.jasper", "Relatorio de professores cadastrados.pdf", professorBean.getListaProfessores());
    }
}

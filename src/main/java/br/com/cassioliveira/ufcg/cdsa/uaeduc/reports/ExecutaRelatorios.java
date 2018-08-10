package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.ProfessorService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
@Named
@ViewScoped
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
    
    private final GeraRelatorios geradorRelatorios = new GeraRelatorios();

    @Inject
    @Getter
    @Setter
    private PendenciaService pendenciaService;

    @Inject
    @Getter
    @Setter
    private ProfessorService professorService;

    /**
     * Emite um relatório com todos os pedidos emitidos em que o status esteja
     * 'ABERTO'
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void emitirRelatorioPendenciasAbertas() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdf("/relatorio_pendencias_abertas.jasper", "Relatório de pendências abertas.pdf", pendenciaService.pendencias("ABERTA"));
    }

    /**
     * Emite um relatório com todos os pedidos emitidos em que o status esteja
     * 'FECHADO'
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void emitirRelatorioPendenciasFechadas() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdf("/relatorio_pendencias_fechadas.jasper", "Relatório de pendências fechadas.pdf", pendenciaService.pendencias("FECHADA"));
    }

    /**
     * Emite um relatório com todos os professores cadastrados.
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void emitirRelatorioTodosProfessores() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdfDownload("/relatorio_professores_cadastrados.jasper", "Relatorio de professores cadastrados.pdf", professorService.findAll());
    }
}

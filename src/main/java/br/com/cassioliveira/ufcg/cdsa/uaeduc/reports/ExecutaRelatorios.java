package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.StatusPendencia;
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
    public void pendenciasAbertas() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdf("/pendencias_abertas.jasper", "Pendências ABERTAS.pdf", pendenciaService.pendencias(StatusPendencia.ABERTA));
    }

    /**
     * Emite um relatório com todos os pedidos emitidos em que o status esteja
     * 'FECHADO'
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void pendenciasFechadas() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdf("/pendencias_fechadas.jasper", "Pendências FECHADAS.pdf", pendenciaService.pendencias(StatusPendencia.FECHADA));
    }

    /**
     * Emite um relatório com todos os professores cadastrados.
     *
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void professoresCadastrados() throws SQLException, JRException, IOException {
        geradorRelatorios.gerarPdf("/professores_cadastrados.jasper", "Professores cadastrados.pdf", professorService.todos());
    }
}

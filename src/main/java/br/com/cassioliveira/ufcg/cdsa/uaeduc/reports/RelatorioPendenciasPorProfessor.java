package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class RelatorioPendenciasPorProfessor implements Serializable {

    @Inject
    private GeneratorReports geradorRelatorios;

    /**
     * Emite o relatório baseado em métodos presentes na classe
     * <b>GeneratorReports</b> e que fazem a conexão com o banco de dados e
     * tratam os dados para gerar os relatórios. Ao chamar essa classe
     * utilitária de geração de relatórios, dois parâmetros são passados, onde o
     * primeiro refere-se ao nome do arquivo de template de relatório compilado
     * (.jasper) e o segundo ao nome do relatório em PDF que o usuário
     * visualizará.
     *
     * @see GeneratorReports
     */
    public void emitir() {
        geradorRelatorios.preparaRelatorioPdf("/relatorio_pendencias_abertas.jasper", "Relatório de pendências por professor.pdf");
    }
}

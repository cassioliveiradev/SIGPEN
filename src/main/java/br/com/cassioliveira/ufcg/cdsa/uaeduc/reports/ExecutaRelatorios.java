package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 * Emite o relatório baseado em métodos presentes na classe
 * <b>GeraRelatorios</b> e que fazem a conexão com o banco de dados e tratam
 * os dados para gerar os relatórios. Ao chamar essa classe utilitária de
 * geração de relatórios, dois parâmetros são passados, onde o primeiro
 * refere-se ao nome do arquivo de template de relatório compilado (.jasper) e o
 * segundo ao nome do relatório em PDF que o usuário visualizará. Cada método
 * dessa classe representa a chamada a um relatorio diferente
 *
 * @see GeraRelatorios
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class ExecutaRelatorios implements Serializable {

    @Inject
    private GeraRelatorios geradorRelatorios;

    /**
     * Emite um relatório com todos os pedidos emitidos em que o status esteja
     * 'ABERTO'
     */
    public void emitirRelatorioPendenciasAbertas() {
        geradorRelatorios.preparaRelatorioPdf("/relatorio_pendencias_abertas.jasper", "Relatorio de pendencias abertas.pdf");
    }

    /**
     * Emite um relatório com todos os pedidos emitidos em que o status esteja
     * 'FECHADO'
     */
    public void emitirRelatorioPendenciasFechadas() {
        geradorRelatorios.preparaRelatorioPdf("/relatorio_pendencias_fechadas.jasper", "Relatorio de pendencias fechadas.pdf");
    }

    /**
     * Emite um relatório com todos os professores cadastrados.
     */
    public void emitirRelatorioTodosProfessores() {
        geradorRelatorios.preparaRelatorioPdf("/relatorio_professores_cadastrados.jasper", "Relatorio de professores cadastrados.pdf");
    }
}

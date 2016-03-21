package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import java.io.IOException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class GeraRelatorios {

    @Inject
    public EntityManager entityManager;

    /**
     * Retorna uma conexão ativa com o banco de dados
     *
     * @return
     */
    public Connection getConexao() {
        return entityManager.unwrap(Connection.class);
    }

    public void fecharConexao() {
        if (getConexao() != null) {
            try {
                getConexao().close();
            } catch (SQLException e) {
                try {
                    throw new SQLException("Erro ao fechar a conexão", e);
                } catch (SQLException ex) {
                    Logger.getLogger(GeraRelatorios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Captura o caminho completo onde os arquivos de template compilados
     * (.jasper) do relatório se encontram, contanto que este arquivo esteja na
     * pasta resources.
     *
     * @return
     */
    public String pegarCaminhoRelatorio() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/relatorios/");
    }

    public void preparaRelatorioPdf(String jasperFileName, String pdfFileName) {
        try {

            //Monta o caminho completo do arquivo de template compilado (.jasper) do relatório.
            String caminhoArquivoJasper = pegarCaminhoRelatorio() + jasperFileName;

            Map<String, Object> parametros = new HashMap<>();

            /*
             * Pega o caminho atual do contexto da aplicação e complementa com o diretório de recursos web do sistema onde a imagem se encontra
             */
            String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ufcg-central-200.png");

            /*
             * Adiciona ao HashMap 'parametros', como chave, o parametro usado no relatório e, como valor, o caminho completo da imagem de logo.
             */
            parametros.put("logo", logo);

            // Inicia a transação com o banco de dados
            entityManager.getTransaction().begin();

            /*
             * O atributo do tipo JasperPrint recebe o resultado do relatório, baseados nos parâmetros do 
             * fillReport que precisa do 'local do arquivo' compilado do relatório (.jasper), possíveis 
             * parâmetros que o relatório necessite e a conexao com o banco
             */
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, getConexao());

            /*
             * Um array de bytes recebe o arquivo já exportado para pdf através do método exportReportToPdf()
             */
            byte[] arquivoPdf = JasperExportManager.exportReportToPdf(jasperPrint);

            /*
             * Precisamos do HttpServletResponse para encaminhar a resposta do da requisição do relatório para o browser do usuário, além
             * de permitir que sejam passadas informações para o cabeçalho da requisição.
             */
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            /*
             * Define, explicitamente, o tipo de saída do relatório.
             */
            response.setContentType("application/pdf");

            /*
             * Define como o relatório será disposto para o usuário no browser e o nome que o arquivo 
             * terá quando o mesmo for salvo localmente. O parâmetro 'inline' exibe o relatório no 
             * navegador do usuário e 'attachment' força o download.
             */
            response.setHeader("Content-disposition", "inline;filename=" + pdfFileName);

            /*
             * O response do HTTPServletReponse recebe o arquivo binário antes de o encaminhar para o browser do usuário.
             */
            response.getOutputStream().write(arquivoPdf);

            // retorna a codificação de caractere utilizada
            response.getCharacterEncoding();

            // Confirma o termino da execução para pagina jsf. Não permitindo alteração após  
            // a execução do relatório
            FacesContext.getCurrentInstance().responseComplete();

            fecharConexao();

        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }
    }

}

package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraRelatorios {

    /**
     * Captura o caminho completo onde os arquivos de template compilados
     * (.jasper) do relatório se encontram, contanto que este arquivo esteja na
     * pasta resources.
     *
     * @return
     */
    public String caminhoRelatorio() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/relatorios/");
    }

    public void gerarPdfDownload(String jasperFileName, String pdfFileName, List<Professor> dados) throws JRException, IOException {

        Map<String, Object> parametros = new HashMap<>();
        String logo = FacesUtil.caminhoContexto("/resources/images/ufcg-central-200.png");
        parametros.put("logo", logo);
        String caminhoArquivoJasper = caminhoRelatorio() + jasperFileName;
        File arquivoJasper = new File(caminhoArquivoJasper);
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, new JRBeanCollectionDataSource(dados));
        HttpServletResponse response = (HttpServletResponse) FacesUtil.responseHTTP();
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void gerarPdf(String jasperFileName, String pdfFileName, List<Pendencia> dados) throws JRException, IOException {

        Map<String, Object> parametros = new HashMap<>();
        String logo = FacesUtil.caminhoContexto("/resources/images/ufcg-central-200.png");
        parametros.put("logo", logo);
        String caminhoArquivoJasper = caminhoRelatorio() + jasperFileName;
        File arquivoJasper = new File(caminhoArquivoJasper);
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, new JRBeanCollectionDataSource(dados));
        HttpServletResponse response = (HttpServletResponse) FacesUtil.responseHTTP();
        
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
    
//    ServletOutputStream stream = response.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//        
//        stream.flush();
//        stream.close();

//            Map<String, Object> parametros = new HashMap<>();
//            String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ufcg-central-200.png");
//            parametros.put("logo", logo);
////            entityManager.getTransaction().begin();
//            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, getConexao());
//            byte[] arquivoPdf = JasperExportManager.exportReportToPdf(jasperPrint);
//
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "inline;filename=" + pdfFileName);
//            response.getOutputStream().write(arquivoPdf);
//            response.getCharacterEncoding();
//            FacesContext.getCurrentInstance().responseComplete();
//            fecharConexao();
//    private final String caminhoRelatorio;
//    private final HttpServletResponse response;
//    private final Map<String, Object> parametros;
//    private final String nomeArquivoSaida;
//
//    public GeraRelatorios(String caminhoRelatorio,
//            HttpServletResponse response, Map<String, Object> parametros,
//            String nomeArquivoSaida) {
//        this.caminhoRelatorio = caminhoRelatorio;
//        this.response = response;
//        this.parametros = parametros;
//        this.nomeArquivoSaida = nomeArquivoSaida;
//    }
//
//    /**
//     *
//     * @param connection
//     * @throws SQLException
//     */
//    @Override
//    public void execute(Connection connection) throws SQLException {
////        Pega o fluxo de dados do arquivo jasper e atribui à variável relatorioStream
//        InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
//
//        try {
////            Possui os dados do relatório dentro dessa variável print
//            JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
//            System.out.println(connection.toString());
//
//            JRExporter exportador = new JRPdfExporter();
//            exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
//            exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            
//            System.out.println(exportador.toString());
//
//            response.setContentType("application/pdf");
//
//            exportador.exportReport();
//        } catch (Exception ex) {
//            throw new SQLException("Erro ao executar o relatório " + this.caminhoRelatorio, ex);
//        }
//    }
//    @Inject
//    public EntityManager entityManager;
//
//    /**
//     * Retorna uma conexão ativa com o banco de dados
//     *
//     * @return
//     */
//    public Connection getConexao() {
//        return entityManager.unwrap(Connection.class);
//    }
//
//    public void fecharConexao() throws SQLException {
//        if (getConexao() != null) {
//            try {
//                getConexao().close();
//            } catch (SQLException e) {
//                throw new SQLException("Erro ao fechar a conexão", e);
//            }
//        }
//    }
//
//
//    public void preparaRelatorioPdf(String jasperFileName, String pdfFileName) throws SQLException {
//        try {
//
//            String caminhoArquivoJasper = caminhoRelatorio() + jasperFileName;
//            Map<String, Object> parametros = new HashMap<>();
//            String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ufcg-central-200.png");
//            parametros.put("logo", logo);
////            entityManager.getTransaction().begin();
//            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, getConexao());
//            byte[] arquivoPdf = JasperExportManager.exportReportToPdf(jasperPrint);
//
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "inline;filename=" + pdfFileName);
//            response.getOutputStream().write(arquivoPdf);
//            response.getCharacterEncoding();
//            FacesContext.getCurrentInstance().responseComplete();
//            fecharConexao();
//
//        } catch (JRException | IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public void preparaRelatorioPdf(String jasperFileName, String pdfFileName) {
//        try {
//
//            //Monta o caminho completo do arquivo de template compilado (.jasper) do relatório.
//            String caminhoArquivoJasper = pegarCaminhoRelatorio() + jasperFileName;
//
//            Map<String, Object> parametros = new HashMap<>();
//
//            /*
//             * Pega o caminho atual do contexto da aplicação e complementa com o diretório de recursos web do sistema onde a imagem se encontra
//             */
//            String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ufcg-central-200.png");
//
//            /*
//             * Adiciona ao HashMap 'parametros', como chave, o parametro usado no relatório e, como valor, o caminho completo da imagem de logo.
//             */
//            parametros.put("logo", logo);
//
//            // Inicia a transação com o banco de dados
//            entityManager.getTransaction().begin();
//
//            /*
//             * O atributo do tipo JasperPrint recebe o resultado do relatório, baseados nos parâmetros do 
//             * fillReport que precisa do 'local do arquivo' compilado do relatório (.jasper), possíveis 
//             * parâmetros que o relatório necessite e a conexao com o banco
//             */
//            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, getConexao());
//
//            /*
//             * Um array de bytes recebe o arquivo já exportado para pdf através do método exportReportToPdf()
//             */
//            byte[] arquivoPdf = JasperExportManager.exportReportToPdf(jasperPrint);
//
//            /*
//             * Precisamos do HttpServletResponse para encaminhar a resposta do da requisição do relatório para o browser do usuário, além
//             * de permitir que sejam passadas informações para o cabeçalho da requisição.
//             */
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//
//            /*
//             * Define, explicitamente, o tipo de saída do relatório.
//             */
//            response.setContentType("application/pdf");
//
//            /*
//             * Define como o relatório será disposto para o usuário no browser e o nome que o arquivo 
//             * terá quando o mesmo for salvo localmente. O parâmetro 'inline' exibe o relatório no 
//             * navegador do usuário e 'attachment' força o download.
//             */
//            response.setHeader("Content-disposition", "inline;filename=" + pdfFileName);
//
//            /*
//             * O response do HTTPServletReponse recebe o arquivo binário antes de o encaminhar para o browser do usuário.
//             */
//            response.getOutputStream().write(arquivoPdf);
//
//            // retorna a codificação de caractere utilizada
//            response.getCharacterEncoding();
//
//            // Confirma o termino da execução para pagina jsf. Não permitindo alteração após  
//            // a execução do relatório
//            FacesContext.getCurrentInstance().responseComplete();
//
//            fecharConexao();
//
//        } catch (JRException | IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}

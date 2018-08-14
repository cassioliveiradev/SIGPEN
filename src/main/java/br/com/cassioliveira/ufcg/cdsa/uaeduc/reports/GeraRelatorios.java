package br.com.cassioliveira.ufcg.cdsa.uaeduc.reports;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
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

    /**
     * Responsável por receber os parâmetros vindos do arquivo jasper, gerar o
     * relatório e procesar o retorno do mesmo de acordo com as requisições HTTP
     * vindas da camada de apresentação. Depois exporta o relatório para o
     * formato PDF antes de apresentar o mesmo no browser.
     *
     * @param jasperFileName
     * @param pdfFileName
     * @param dados
     * @throws JRException
     * @throws IOException
     */
    public void gerarPdf(String jasperFileName, String pdfFileName, List<?> dados) throws JRException, IOException {

        Map<String, Object> parametros = new HashMap<>();
        String ufcg = FacesUtil.caminhoContexto("/resources/images/ufcg.png");
        String cdsa = FacesUtil.caminhoContexto("/resources/images/cdsa.png");
        parametros.put("ufcg", ufcg);
        parametros.put("cdsa", cdsa);
        String caminhoArquivoJasper = caminhoRelatorio() + jasperFileName;

        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametros, new JRBeanCollectionDataSource(dados));
        HttpServletResponse response = (HttpServletResponse) FacesUtil.responseHTTP();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=".concat(pdfFileName));

        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
}

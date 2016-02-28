package br.com.cassioliveira.ufcg.cdsa.uaeduc.converter;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PendenciaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Esta classe representa o conversor de Pendencia, cujo objetivo é converter
 * um valor enviando pela View em objeto ou retornar o ID do objeto. 
 * Ao implementar a interface Converter dois métodos são implementados, 
 * getAsObject e getAsString.
 * 
 * @author cassio <cassio@cassioliveira.com.br>
 */
@FacesConverter(forClass = Pendencia.class)
public class PendenciaConverter implements Converter {

    private final PendenciaService pendenciaService;

    /**
     * Enquanto a versão atual do JSF (2.2) não suporta injeção com dentro de
     * conversores, essa classe utilitária CDIServiceLocator, faz esse papel.
     */
    public PendenciaConverter() {
        this.pendenciaService = CDIServiceLocator.getBean(PendenciaService.class);
    }

    /**
     * Este método recebe a String e devolve o Object. Quando uma Pendencia
     * for mostrada na tela será seu ID que estará sendo exibido.
     * 
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Pendencia objectToReturn = null;

        if (value != null) {
            objectToReturn = this.pendenciaService.findById(new Long(value));
        }
        return objectToReturn;
    }

    /**
     * Este método recebe o Object e devolve a String. Apartir dessa String 
     * recuperamos o Object que esta ligado ao modelo.
     * 
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Long code = ((Pendencia) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
}

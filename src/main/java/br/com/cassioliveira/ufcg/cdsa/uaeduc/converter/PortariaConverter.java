package br.com.cassioliveira.ufcg.cdsa.uaeduc.converter;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Portaria;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.PortariaService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Esta classe representa o conversor de Portaria, cujo objetivo é converter
 * um valor enviando pela View em objeto ou retornar o ID do objeto. 
 * Ao implementar a interface Converter dois métodos são implementados, 
 * getAsObject e getAsString.
 * 
 * @author cassio <cassio@cassioliveira.com.br>
 */
@FacesConverter(forClass = Portaria.class)
public class PortariaConverter implements Converter {

    private final PortariaService portariaService;

    /**
     * Enquanto a versão atual do JSF (2.2) não suporta injeção com dentro de
     * conversores, essa classe utilitária CDIServiceLocator, faz esse papel.
     */
    public PortariaConverter() {
        this.portariaService = CDIServiceLocator.getBean(PortariaService.class);
    }

    /**
     * Este método recebe a String e devolve o Object. Quando uma Portaria
     * for mostrada na tela será seu ID que estará sendo exibido.
     * 
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Portaria objectToReturn = null;

        if (value != null) {
            objectToReturn = this.portariaService.porId(new Long(value));
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
            Long code = ((Portaria) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
}

package br.com.cassioliveira.ufcg.cdsa.uaeduc.converter;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.ProfessorService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Esta classe representa o conversor de Professor, cujo objetivo é converter um
 * valor enviando pela View em objeto ou retornar o ID do objeto. Ao implementar
 * a interface Converter dois métodos são implementados, getAsObject e
 * getAsString.
 *
 * @author cassio <cassio@cassioliveira.com.br>
 */
@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {

    private final ProfessorService professorService;

    /**
     * Enquanto a versão atual do JSF (2.2) não suporta injeção com dentro de
     * conversores, essa classe utilitária CDIServiceLocator, faz esse papel.
     */
    public ProfessorConverter() {
        this.professorService = CDIServiceLocator.getBean(ProfessorService.class);
    }

    /**
     * Este método recebe a String e devolve o Object. Quando uma Professor for
     * mostrada na tela será seu ID que estará sendo exibido.
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Professor objectToReturn = null;

        if (value != null) {
            objectToReturn = this.professorService.findById(new Long(value));
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
            Long code = ((Professor) value).getId();
            return code == null ? null : code.toString();
        }
        return "";
    }
}

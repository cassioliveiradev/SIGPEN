package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.ProfessorService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class ProfessorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Professor professor;

    @Getter
    @Setter
    private Professor professorSelecionado;

    @Inject
    @Getter
    @Setter
    private ProfessorService professorService;

    @Getter
    private List<Professor> listaProfessores;

    public ProfessorBean() {
        this.professor = new Professor();
        this.professorSelecionado = new Professor();
    }

    @PostConstruct
    public void init() {
        this.listaProfessores = professorService.findAll();
    }

    /**
     * Permite que seja adicionado um novo professor ou que o nome do professor
     * existente seja alterado.
     */
    public void editar() {
        this.professorService.editar(professor);
        FacesUtil.mensagemSucesso("Professor atualizado com sucesso!");
        FacesUtil.redirecionaPara("cadastro-professor.xhtml");
        professor = new Professor();
    }

    /**
     * Remove um professor cadastrado
     */
    public void delete() {
        this.professorService.delete(professorSelecionado);
        FacesUtil.redirecionaPara("cadastro-professor.xhtml");
        FacesUtil.mensagemSucesso("Professor excluido com sucesso!");
    }

    /**
     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
     *
     * @return
     */
    public boolean getEditando() {
        return this.professor.getId() != null;
    }

}

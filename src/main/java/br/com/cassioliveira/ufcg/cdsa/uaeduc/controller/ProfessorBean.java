package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.CategoriasServidor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.Estados;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.ProfessorService;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.Arrays;
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
    private List<CategoriasServidor> categorias;

    @Getter
    private List<Estados> estados;

    @Getter
    private List<Professor> listaProfessores;

    public ProfessorBean() {
        this.professor = new Professor();
        this.professorSelecionado = new Professor();
    }

    @PostConstruct
    public void init() {
        this.listaProfessores = professorService.findAll();
        this.categorias = Arrays.asList(CategoriasServidor.values());
        this.estados = Arrays.asList(Estados.values());
    }

    /**
     * Permite que seja adicionado um novo professor ou que o nome do professor
     * existente seja alterado.
     */
    public void salvar() {
        this.professorService.editar(professor);
        FacesUtil.mensagemSucesso("Salvo com sucesso!");
        this.listaProfessores = professorService.findAll();
        getLimpar();
    }

    /**
     * Remove um professor cadastrado
     */
    public void delete() {
        this.professorService.delete(professorSelecionado);
        this.listaProfessores = professorService.findAll();
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

    /**
     * Cria uma nova instância do professor.
     */
    public void getLimpar() {
        this.professor = new Professor();
    }

}

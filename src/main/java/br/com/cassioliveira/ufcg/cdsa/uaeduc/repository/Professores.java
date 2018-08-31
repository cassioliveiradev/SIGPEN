package br.com.cassioliveira.ufcg.cdsa.uaeduc.repository;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Professor;
import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class Professores extends Generico<Professor> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Professores() {
        super(Professor.class);
    }

//    public Professor localizaProfessor(Long id) {
//        Professor professor = new Professor();
//        String nomeProfessor = getEntityManager().createNativeQuery("SELECT pessoa.nome FROM professor,pessoa WHERE pessoa.id=professor.id AND professor.id=:id").setParameter("id", id).toString();
//        professor.setNome(nomeProfessor);
//        return professor;
//    }

}

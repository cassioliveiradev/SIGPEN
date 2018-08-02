package br.com.cassioliveira.ufcg.cdsa.uaeduc.repository;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class Pendencias extends Generico<Pendencia> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Pendencias() {
        super(Pendencia.class);
    }
    
    public List<Pendencia> pendenciasAbertas(String status) {
        List<Pendencia> pendenciasAbertas = new ArrayList<>();
        getEntityManager().createNamedQuery("Pendencias.abertas", Pendencia.class).setParameter("status", status).getResultList();
        return pendenciasAbertas;
    }

//    public List<String> pendenciasPorProfessor(String professor) {
//        List<String> pendencias = new ArrayList<>();
//        getEntityManager().createNamedQuery("Pendencias.porProfessor", Pendencia.class).setParameter("nomeProfessor", professor).getResultList();
//        return pendencias;
//    }
}

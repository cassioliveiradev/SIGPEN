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

//    public List<Pendencia> pendencias() {
//        List<Pendencia> pendencias;
//        pendencias = getEntityManager().createNativeQuery("SELECT * FROM pendencia p ORDER BY professor ASC").getResultList();
//        System.out.println("REPOSITORY: #################################: " + pendencias);
////        getEntityManager().createNativeQuery("SELECT p.professor, p.descricao, data_recebimento, p.data_entrega_destinatario FROM pendencia p WHERE status=:status ORDER BY professor ASC");
//        return pendencias;
//    }
//    public List<Pendencia> pendencias(String status) {
//        List<Pendencia> pendenciasAbertas;
//        pendenciasAbertas = getEntityManager().createNamedQuery("Pendencias.abertas", Pendencia.class).setParameter("status", status).getResultList();
//        System.out.println(pendenciasAbertas + "DAO: ###############>>>>>>>>>>>>>>>>>>>>");
//        return pendenciasAbertas;
//    }
    public List<Pendencia> pendenciasPorProfessor(String professor) {
        List<Pendencia> pendenciasPorProfessor;
        pendenciasPorProfessor = getEntityManager().createQuery("SELECT p.descricao FROM Pendencia p WHERE p.professor=:professor").getResultList();
        return pendenciasPorProfessor;
    }
}

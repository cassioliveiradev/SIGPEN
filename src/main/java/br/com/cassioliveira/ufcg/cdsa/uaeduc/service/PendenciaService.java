package br.com.cassioliveira.ufcg.cdsa.uaeduc.service;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.StatusPendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.exception.NegocioException;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.repository.Pendencias;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class PendenciaService implements Serializable {

    public static final long serialVersionUID = 1L;

    @Inject
    private Pendencias pendencias;

    public PendenciaService() {
    }

    /**
     * Método utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param pendencia
     */
    @Transactional
    public void salvar(Pendencia pendencia) {
        pendencia.setStatus(StatusPendencia.ABERTA);
        this.pendencias.salvar(pendencia);
    }

    @Transactional
    public void editar(Pendencia pendencia) {
        this.pendencias.editar(pendencia);
    }

    /**
     * Método utilizado para remover um cadastro do banco de dados.
     *
     * @param pendencia
     * @throws NegocioException
     */
    @Transactional
    public void delete(Pendencia pendencia) throws NegocioException {
        pendencias.delete(findById(pendencia.getId()));
    }

    /**
     * Método utilizado para buscar um registro no banco de dados para
     * determinada entidade através da passagem do seu ID como parâmetro.
     *
     * @param id
     * @return
     */
    public Pendencia findById(Long id) {
        return pendencias.findById(id);
    }

    /**
     * Método responsável pela busca em toda lista.Método utilizado para
     * retornar uma lista com todos os resultados encontrados no banco de dados
     * para a entidade que a chamar.
     *
     * @return
     */
    public List<Pendencia> findAll() {
        return pendencias.findAll();
    }

    /**
     * Recebe o valor passado pelo método buscarPorCampo() para determinar a
     * duplicidade do cadastro e lança uma exceção informando ao usuário qual
     * campo não pode ser inserido por já existir no banco de dados.
     *
     * @param campo
     * @param valor
     * @param id
     * @param pendencia
     */
    public void checaCampoDuplicado(String campo, Object valor, Long id, Pendencia pendencia) {
        pendencias.checaCampoDuplicado(campo, valor, null, pendencia);
    }

    public List<Pendencia> pendencias(String status) {
        ArrayList<Pendencia> pendenciasAbertas = new ArrayList<>();
        ArrayList<Pendencia> pendenciasFechadas = new ArrayList<>();
        for (Pendencia pendencia : findAll()) {
            if (status.equals(pendencia.getStatus().ABERTA.toString())) {
                pendenciasAbertas.add(pendencia);
            } else {
                pendenciasFechadas.add(pendencia);
            }
        }
        if (status.equals(StatusPendencia.ABERTA.toString())) {
            return pendenciasAbertas;
        }

        return pendenciasFechadas;
    }

//    public List<String> pendenciasPorProfessor(String professor){
//        return pendencias.pendenciasPorProfessor(professor);
//    ********************status.equals(StatusPendencia.ABERTA.toString())
//    }
}

package br.com.cassioliveira.ufcg.cdsa.uaeduc.service;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.exception.NegocioException;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Portaria;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.repository.Portarias;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class PortariaService implements Serializable {

    public static final long serialVersionUID = 1L;

    @Inject
    private Portarias portarias;

    public PortariaService() {
    }

    /**
     * Método utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param portaria
     */
    @Transactional
    public void salvar(Portaria portaria) {
        this.portarias.salvar(portaria);
    }

    @Transactional
    public void editar(Portaria portaria) {
        this.portarias.editar(portaria);
    }

    /**
     * Método utilizado para remover um cadastro do banco de dados.
     *
     * @param portaria
     * @throws NegocioException
     */
    @Transactional
    public void excluir(Portaria portaria) throws NegocioException {
        portarias.excluir(porId(portaria.getId()));
    }

    /**
     * Método utilizado para buscar um registro no banco de dados para
     * determinada entidade através da passagem do seu ID como parâmetro.
     *
     * @param id
     * @return
     */
    public Portaria porId(Long id) {
        return portarias.porId(id);
    }

    /**
     * Método responsável pela busca em toda lista.Método utilizado para
     * retornar uma lista com todos os resultados encontrados no banco de dados
     * para a entidade que a chamar.
     *
     * @return
     */
    public List<Portaria> todas() {
        return portarias.todos();
    }

    /**
     * Recebe o valor passado pelo método buscarPorCampo() para determinar a
     * duplicidade do cadastro e lança uma exceção informando ao usuário qual
     * campo não pode ser inserido por já existir no banco de dados.
     *
     * @param campo
     * @param valor
     * @param id
     * @param portaria
     */
    public void checaCampoDuplicado(String campo, Object valor, Long id, Portaria portaria) {
        portarias.checaCampoDuplicado(campo, valor, null, portaria);
    }

}

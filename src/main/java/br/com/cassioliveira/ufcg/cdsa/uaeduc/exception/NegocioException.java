package br.com.cassioliveira.ufcg.cdsa.uaeduc.exception;

/**
 * A classe NegocioException representa um tipo de exceção usado para
 * representar erros de negócio.
 *
 * @author cassio <cassio@cassioliveira.com.br>
 */
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Classe destinada a servir de ponte para tratar exceções de negócio.
     *
     * @param message
     */
    public NegocioException(String message) {
        super(message);
    }
}

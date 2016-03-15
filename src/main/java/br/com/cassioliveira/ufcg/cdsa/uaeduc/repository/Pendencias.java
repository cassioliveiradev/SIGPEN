package br.com.cassioliveira.ufcg.cdsa.uaeduc.repository;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Pendencia;
import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class Pendencias extends Generico<Pendencia> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Pendencias() {
        super(Pendencia.class);
    }
}

package br.com.cassioliveira.ufcg.cdsa.uaeduc.repository;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Portaria;
import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class Portarias extends Generico<Portaria> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Portarias() {
        super(Portaria.class);
    }
}

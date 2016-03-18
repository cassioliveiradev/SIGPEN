package br.com.cassioliveira.ufcg.cdsa.uaeduc.repository;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Login;
import java.io.Serializable;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public class Logins extends Generico<Login> implements Serializable {

    public Logins() {
        super(Login.class);
    }
}

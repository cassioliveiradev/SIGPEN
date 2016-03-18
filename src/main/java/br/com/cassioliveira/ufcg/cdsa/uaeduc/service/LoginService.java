package br.com.cassioliveira.ufcg.cdsa.uaeduc.service;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.exception.NegocioException;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Login;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.repository.Logins;
import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author cassio
 */
public class LoginService implements Serializable {

    @Inject
    private Logins logins;

    @Transactional
    public void save(Login product) throws NegocioException {
        this.logins.salvar(product);
    }

    @Transactional
    public void delete(Login product) throws NegocioException {
        logins.delete(findById(product.getId()));
    }

    public Login findById(Long id) {
        return logins.findById(id);
    }

    public List<Login> findAll() {
        return logins.findAll();
    }

}

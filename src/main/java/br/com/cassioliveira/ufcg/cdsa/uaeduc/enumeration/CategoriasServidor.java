package br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration;

import java.io.Serializable;
import lombok.Getter;

/**
 *
 * @author cassio
 */
public enum CategoriasServidor implements Serializable {

    EFETIVO("Efetivo"),
    SUBSTITUTO("Substituto"),
    VISITANTE("Visitante");

    @Getter
    private final String description;

    CategoriasServidor(String description) {
        this.description = description;
    }
}

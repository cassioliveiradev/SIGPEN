package br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration;

import lombok.Getter;

/**
 * Define os possíveis status para os agendamentos. O status 'Aberta' indica o
 * momento em que a pendência ainda não foi finalizada para o professor
 * associado. O status 'Fechada' indica a pendência já finalizada. Uma vez que
 * nenhuma pendência pode ser apagada para fins de consultas futuras, esse modo
 * do status permite que a exibição dos dois tipos de pendência sejam feitas de
 * forma controlada, mostrando apenas um ou outro grupo, se necessário.
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public enum StatusPendencia {

    ABERTA("Aberta"),
    FECHADA("Fechada");

    @Getter
    private final String descricao;

    StatusPendencia(String descricao) {
        this.descricao = descricao;
    }

}

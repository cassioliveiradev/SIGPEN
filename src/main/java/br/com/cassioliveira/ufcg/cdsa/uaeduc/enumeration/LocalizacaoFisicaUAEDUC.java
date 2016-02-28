package br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration;

import lombok.Getter;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public enum LocalizacaoFisicaUAEDUC {
    
    
    PASTAPROFESSORES("Pasta professores"),
    PASTAREGIMEDOMICILIAR("Pasta regime domiciliar"),
    PASTADISPENSADISCIPLINA("Pasta dispensa disciplina"),
    PASTACOORDENACAOADMINISTRATIVA("Pasta Coord. Administrativa"),
    PASTAEDUCACAODOCAMPO("Pasta Educação do Campo"),
    PASTACIENCIASSOCIAIS("Pasta Ciências Sociais"),
    PASTAGESTAOPUBLICA("Pasta Gestão Pública"),
    PASTAFREQUENCIA("Pasta Frequência"),
    PASTASOLICITACAODOALUNO("Pasta solicitação do aluno"),
    MESAVANIA("Mesa Vania"),
    MESATARSILA("Mesa Tarsila"),
    OUTRO("Outro");
    
    @Getter
    private final String descricao;
    
    LocalizacaoFisicaUAEDUC(String descricao){
        this.descricao = descricao;
    }    
    
}

package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import br.com.cassioliveira.ufcg.cdsa.uaeduc.enumeration.Estados;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class Endereco implements Serializable {

    public static final long serialVersionUID = 1L;

    @Column(name = "endereco_rua", length = 200)
    private String rua;

    @Column(name = "endereco_numero", length = 10)
    private String numero;

    @Column(name = "endereco_complemento", length = 200)
    private String complemento;

    @Column(name = "endereco_bairro", length = 50)
    private String bairro;

    @Enumerated(EnumType.STRING)
    @Column(name = "endereco_estado")
    private Estados estado;

    @Column(name = "endereco_cidade", length = 70)
    private String cidade;

    @Column(name = "endereco_cep", length = 10)
    private String cep;

}

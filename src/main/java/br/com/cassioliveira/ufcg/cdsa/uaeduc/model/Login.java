package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author cassio
 */
@Entity
@Data
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String userName;
    
    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "password", nullable = false, length = 30)
    private String password;
    
    @Column(name = "login_date_time_register")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeRegister;
}

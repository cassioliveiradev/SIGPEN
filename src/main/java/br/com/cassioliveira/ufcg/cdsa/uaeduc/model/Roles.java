package br.com.cassioliveira.ufcg.cdsa.uaeduc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author cassio
 */
@Entity
@Data
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Login id;

    @NotNull
    @Column(name = "user_role", nullable = false, length = 50)
    private String role;

    @Column(name = "roles_date_time_register")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeRegister;
}

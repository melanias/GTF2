package br.com.sti.gtf.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.caelum.vraptor.serialization.SkipSerialization;

import br.com.sti.gtf.enums.EmployeeStatus;
import br.com.sti.gtf.enums.Role;

/**
 *
 * @author Phelipe Melanias
 */
@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @Column(length=14, unique=true, nullable=false)
    private String cpf;

    @Column(length=200, unique=true, nullable=false)
    private String nome;

    @Column(length=200)
    private String email;

    @SkipSerialization
    @Column(length=128, nullable=false)
    private String senha;

    @SkipSerialization
    @Transient
    private String checkPassword;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition="smallint", nullable=false)
    private Role perfil;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition="smallint", nullable=false)
    private EmployeeStatus status;

    //getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = ((nome == null) ? nome : nome.trim());
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = ((email == null) ? email : email.trim());
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) {
        this.senha = ((senha == null) ? senha : senha.trim());
    }

    public String getCheckPassword() { return checkPassword; }
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = ((checkPassword == null) ? checkPassword
                                                      : checkPassword.trim());
    }

    public Role getPerfil() { return perfil; }
    public void setPerfil(Role perfil) { this.perfil = perfil; }

    public EmployeeStatus getStatus() { return status; }
    public void setStatus(EmployeeStatus status) { this.status = status; }

    public String getFirstAndLastName() {
        String[] s = nome.split(" ");
        return ((s.length > 2) ? s[0] +" "+ s[s.length-1] : nome);
    }
}
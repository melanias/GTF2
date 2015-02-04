package br.com.sti.gtf.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.sti.gtf.validation.CorValidation;

/**
 *
 * @author Phelipe Melanias
 */
@Entity
@Table(name="cor")
@CorValidation
public class Cor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message="{cor.nome.NotNull}")
    @Column(length=50, nullable=false)
    private String nome;

    //getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null) {
            this.nome = nome;
        } else {
            this.nome = nome.trim();
        }
    }
}
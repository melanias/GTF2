package br.com.sti.gtf.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.sti.gtf.validation.CombustivelValidation;

/**
 *
 * @author Phelipe Melanias
 */
@Entity
@Table(name="combustivel")
@CombustivelValidation
public class Combustivel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message="{combustivel.nome.NotNull}")
    @Column(length=100, nullable=false)
    private String nome;

//    @OneToMany(mappedBy="combustivel")
//    private List<Veiculo> veiculos;

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

//    public List<Veiculo> getVeiculos() { return veiculos; }
//    public void setVeiculos(List<Veiculo> veiculos) { this.veiculos = veiculos; }
}
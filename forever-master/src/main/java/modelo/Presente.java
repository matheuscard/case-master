package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Matheus Cardozo
 */
@Dependent
@Entity
@Table(name = "presentes")
@NamedQueries({
    @NamedQuery(name = "Presente.buscarTodos", 
            query = "SELECT p FROM Presente p"),
    @NamedQuery(name = "Presente.presentesMaisCaros",
            query = "SELECT p FROM Presente p ORDER BY p.valor DESC"),
    @NamedQuery(name = "Presente.presentesPorCategoria",
            query = "SELECT p FROM Presente p WHERE p.categoria = :categoria"),
    @NamedQuery(name = "Presente.buscarPorNome", 
            query = "SELECT p FROM Presente p WHERE p.descricao LIKE :nome"),
    @NamedQuery(name = "Presente.valorMinimo",
            query = "SELECT p FROM Presente p WHERE p.valor >= :valor")
})
public class Presente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presente_gen")
    @SequenceGenerator(name = "presente_gen", sequenceName = "presente_seq", initialValue = 100)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @Column(nullable = false, precision = 2)
    private Double valor;
    
    @ManyToOne
    @JoinColumn(name = "fk_categoria", referencedColumnName = "id")
    private Categoria categoria;

    public Presente() {}

    public Presente(String descricao, Double valor, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Presente other = (Presente) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Presente{" + "descricao=" + descricao + ", valor=" + valor + ", categoria=" + categoria + '}';
    }
}

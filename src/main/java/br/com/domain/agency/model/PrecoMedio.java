package br.com.domain.agency.model;

import javax.persistence.*;

@Entity
@Table(name = "precos_medios")
public class PrecoMedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor")
    private float valor;

    @ManyToOne
    @JoinColumn(name="submercado_id", insertable = false, nullable = false, updatable = false)
    @OrderColumn(name="submercado_id")
    private Submercado submercado;

    public PrecoMedio() { }

    public PrecoMedio(float valor, Submercado submercado) {
        this.valor = valor;
        this.submercado = submercado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Submercado getSubmercado() {
        return submercado;
    }

    public void setSubmercado(Submercado submercado) {
        this.submercado = submercado;
    }
}

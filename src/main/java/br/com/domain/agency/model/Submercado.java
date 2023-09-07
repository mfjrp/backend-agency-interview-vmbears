package br.com.domain.agency.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "submercados")
public class Submercado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "regiao")
    private String regiao;

    @ManyToOne
    @JoinColumn(name="agente_id", insertable = false, nullable = false, updatable = false)
    private Agente agente;

    @Column(name = "geracao")
    @OneToMany(mappedBy = "submercado")
    private List<Geracao> geracoes;
    @Column(name = "compra")
    @OneToMany(mappedBy = "submercado")
    private List<Compra> compras;
    @Column(name = "preco_medio")
    @OneToMany(mappedBy = "submercado")
    private List<PrecoMedio> valores;

    public Submercado() { }

    public Submercado(String regiao, Agente agente, List<Geracao> geracoes, List<Compra> compras, List<PrecoMedio> valores) {
        this.regiao = regiao;
        this.agente = agente;
        this.geracoes = geracoes;
        this.compras = compras;
        this.valores = valores;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public List<Geracao> getGeracoes() {
        return geracoes;
    }

    public void setGeracoes(List<Geracao> geracoes) {
        this.geracoes = geracoes;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<PrecoMedio> getValores() {
        return valores;
    }

    public void setValores(List<PrecoMedio> valores) {
        this.valores = valores;
    }
}

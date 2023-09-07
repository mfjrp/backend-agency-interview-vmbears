package br.com.domain.agency.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "agentes")
@XmlType(propOrder = {"codigo", "data", "regiao"})
@Entity
@Table(name = "agentes")
public class Agente {
    @Id
    private long codigo;
    @Column(name = "data")
    private Date data;
    @Column(name = "regiao")
    @OneToMany(mappedBy = "agente")
    private List<Submercado> regioes;

    public Agente() { }

    public Agente(Date data, List<Submercado> regioes) {
        this.data = data;
        this.regioes = regioes;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Submercado> getRegiao() {
        return regioes;
    }

    public void setRegiao(List<Submercado> regioes) {
        this.regioes = regioes;
    }
}

package br.com.domain.agency.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Agentes {

    private String versao;
    private List<Agente> agente;

    public Agentes() { }

    public Agentes(String versao, List<Agente> agente) {
        this.versao = versao;
        this.agente = agente;
    }

    public List<Agente> getAgente() {
        return agente;
    }

    public void setAgente(List<Agente> agente) {
        this.agente = agente;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}

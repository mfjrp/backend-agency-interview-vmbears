package br.com.domain.agency.controller;

import br.com.domain.agency.model.Agente;
import br.com.domain.agency.repository.AgenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class AgentController {

    @Autowired
    private AgenteRepository agenteRepository;

    // get all agents
    @GetMapping("/agents")
    public List<Agente> getAllAgents() {
        return agenteRepository.findAll();
    }
}

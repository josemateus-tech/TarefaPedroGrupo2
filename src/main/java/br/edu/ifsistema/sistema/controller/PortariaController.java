package br.edu.ifsistema.sistema.controller;

import br.edu.ifsistema.sistema.model.enums.TipoUsuario;
import br.edu.ifsistema.sistema.repository.*;
import br.edu.ifsistema.sistema.service.LiberacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/portaria")
public class PortariaController {
    private final LiberacaoRepository liberacoes; private final UsuarioRepository usuarios; private final RegistroPortariaRepository registros; private final LiberacaoService service;
    public PortariaController(LiberacaoRepository l,UsuarioRepository u,RegistroPortariaRepository r,LiberacaoService s){liberacoes=l;usuarios=u;registros=r;service=s;}
    @GetMapping public String tela(Model model){model.addAttribute("liberacoes",liberacoes.findAll());model.addAttribute("porteiros",usuarios.findByTipoOrderByNomeAsc(TipoUsuario.PORTEIRO));model.addAttribute("registros",registros.findAll());return "portaria";}
    @PostMapping("/{id}/saida") public String saida(@PathVariable Long id,@RequestParam Long porteiroId){service.registrarSaida(id,porteiroId);return "redirect:/portaria";}
    @PostMapping("/{id}/retorno") public String retorno(@PathVariable Long id){service.registrarRetorno(id);return "redirect:/portaria";}
}

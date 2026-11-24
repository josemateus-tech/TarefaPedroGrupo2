package br.edu.ifsistema.sistema.controller;

import br.edu.ifsistema.sistema.model.enums.*;
import br.edu.ifsistema.sistema.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PainelController {
    private final UsuarioRepository usuarios; private final LiberacaoRepository liberacoes;
    public PainelController(UsuarioRepository u,LiberacaoRepository l){usuarios=u;liberacoes=l;}
    @GetMapping("/") public String inicio(Model model){
        model.addAttribute("totalUsuarios",usuarios.count());
        model.addAttribute("totalAlunos",usuarios.countByTipo(TipoUsuario.ALUNO));
        model.addAttribute("pendentes",liberacoes.countByStatus(StatusLiberacao.PENDENTE));
        model.addAttribute("aprovadas",liberacoes.countByStatus(StatusLiberacao.APROVADA));
        model.addAttribute("ultimas",liberacoes.findTop10ByOrderByDataSolicitacaoDesc());
        return "index";
    }
}

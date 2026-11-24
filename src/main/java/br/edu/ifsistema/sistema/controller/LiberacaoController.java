package br.edu.ifsistema.sistema.controller;

import br.edu.ifsistema.sistema.model.Liberacao;
import br.edu.ifsistema.sistema.model.enums.TipoUsuario;
import br.edu.ifsistema.sistema.repository.UsuarioRepository;
import br.edu.ifsistema.sistema.service.LiberacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/liberacoes")
public class LiberacaoController {
    private final LiberacaoService service; private final UsuarioRepository usuarios;
    public LiberacaoController(LiberacaoService s,UsuarioRepository u){service=s;usuarios=u;}
    @GetMapping("/nova") public String nova(Model model){model.addAttribute("liberacao",new Liberacao());model.addAttribute("alunos",usuarios.findByTipoOrderByNomeAsc(TipoUsuario.ALUNO));model.addAttribute("professores",usuarios.findByTipoOrderByNomeAsc(TipoUsuario.PROFESSOR));model.addAttribute("liberacoes",service.listar());return "aquisicao";}
    @PostMapping public String salvar(@ModelAttribute Liberacao liberacao){
        liberacao.setAluno(usuarios.findById(liberacao.getAluno().getId()).orElseThrow());
        liberacao.setProfessor(usuarios.findById(liberacao.getProfessor().getId()).orElseThrow());
        service.salvar(liberacao);return "redirect:/liberacoes/nova";
    }
    @GetMapping("/autorizacoes") public String autorizacoes(Model model){model.addAttribute("liberacoes",service.pendentes());return "autorizacao";}
    @PostMapping("/{id}/aprovar") public String aprovar(@PathVariable Long id){service.decidir(id,true);return "redirect:/liberacoes/autorizacoes";}
    @PostMapping("/{id}/recusar") public String recusar(@PathVariable Long id){service.decidir(id,false);return "redirect:/liberacoes/autorizacoes";}
}

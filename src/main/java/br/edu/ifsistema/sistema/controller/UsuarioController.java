
package br.edu.ifsistema.sistema.controller;

import br.edu.ifsistema.sistema.model.Usuario;
import br.edu.ifsistema.sistema.model.Turma;
import br.edu.ifsistema.sistema.model.enums.TipoUsuario;
import br.edu.ifsistema.sistema.repository.TurmaRepository;
import br.edu.ifsistema.sistema.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final TurmaRepository turmas;

    public UsuarioController(UsuarioService s, TurmaRepository t) {
        service = s;
        turmas = t;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipos", TipoUsuario.values());
        model.addAttribute("turmas", turmas.findAll());
        return "usuario";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", service.buscar(id));
        model.addAttribute("usuarios", service.listar());
        model.addAttribute("tipos", TipoUsuario.values());
        model.addAttribute("turmas", turmas.findAll());
        return "usuario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Usuario usuario,
                         BindingResult result,
                         Model model) {

        // Campos vazios devem ser enviados como null
        if (usuario.getMatricula() != null && usuario.getMatricula().isBlank()) {
            usuario.setMatricula(null);
        }

        if (usuario.getSiape() != null && usuario.getSiape().isBlank()) {
            usuario.setSiape(null);
        }

        // Recupera uma turma existente no banco
        if (usuario.getTurma() != null && usuario.getTurma().getId() != null) {
            Turma turma = turmas.findById(usuario.getTurma().getId()).orElse(null);
            usuario.setTurma(turma);
        } else {
            usuario.setTurma(null);
        }

        if (result.hasErrors()) {
            model.addAttribute("usuarios", service.listar());
            model.addAttribute("tipos", TipoUsuario.values());
            model.addAttribute("turmas", turmas.findAll());
            return "usuario";
        }

        service.salvar(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/usuarios";
    }
}

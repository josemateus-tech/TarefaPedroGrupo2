package br.edu.ifsistema.sistema.controller;

import br.edu.ifsistema.sistema.model.Turma;
import br.edu.ifsistema.sistema.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaRepository repo;
    public TurmaController(TurmaRepository repo){this.repo=repo;}
    @GetMapping public String listar(Model model){model.addAttribute("turmas",repo.findAll());model.addAttribute("turma",new Turma());return "turma";}
    @PostMapping public String salvar(@Valid @ModelAttribute Turma turma, BindingResult result, Model model){
        if(result.hasErrors()){model.addAttribute("turmas",repo.findAll());return "turma";}
        repo.save(turma);return "redirect:/turmas";
    }
}

package br.edu.ifsistema.sistema.service;

import br.edu.ifsistema.sistema.model.Usuario;
import br.edu.ifsistema.sistema.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    public UsuarioService(UsuarioRepository repo){this.repo=repo;}
    public List<Usuario> listar(){return repo.findAll();}
    @Transactional public Usuario salvar(Usuario usuario){return repo.save(usuario);}
    public Usuario buscar(Long id){return repo.findById(id).orElseThrow(()->new IllegalArgumentException("Usuário não encontrado."));}
    public void excluir(Long id){repo.deleteById(id);}
}

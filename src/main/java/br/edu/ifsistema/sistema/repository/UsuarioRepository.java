package br.edu.ifsistema.sistema.repository;

import br.edu.ifsistema.sistema.model.Usuario;
import br.edu.ifsistema.sistema.model.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoOrderByNomeAsc(TipoUsuario tipo);
    long countByTipo(TipoUsuario tipo);
}

package br.edu.ifsistema.sistema.repository;

import br.edu.ifsistema.sistema.model.Liberacao;
import br.edu.ifsistema.sistema.model.enums.StatusLiberacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LiberacaoRepository extends JpaRepository<Liberacao,Long> {
    List<Liberacao> findTop10ByOrderByDataSolicitacaoDesc();
    List<Liberacao> findByStatusOrderByDataSolicitacaoDesc(StatusLiberacao status);
    long countByStatus(StatusLiberacao status);
}

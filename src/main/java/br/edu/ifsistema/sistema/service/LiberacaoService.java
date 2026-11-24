package br.edu.ifsistema.sistema.service;

import br.edu.ifsistema.sistema.model.*;
import br.edu.ifsistema.sistema.model.enums.StatusLiberacao;
import br.edu.ifsistema.sistema.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LiberacaoService {
    private final LiberacaoRepository liberacoes;
    private final UsuarioRepository usuarios;
    private final RegistroPortariaRepository registros;
    public LiberacaoService(LiberacaoRepository l, UsuarioRepository u, RegistroPortariaRepository r){liberacoes=l;usuarios=u;registros=r;}

    public List<Liberacao> listar(){return liberacoes.findAll();}
    public List<Liberacao> pendentes(){return liberacoes.findByStatusOrderByDataSolicitacaoDesc(StatusLiberacao.PENDENTE);}
    public Liberacao buscar(Long id){return liberacoes.findById(id).orElseThrow(()->new IllegalArgumentException("Liberação não encontrada."));}
    @Transactional public Liberacao salvar(Liberacao l){
        if(l.getSaidaPrevista()!=null && l.getRetornoPrevisto()!=null && l.getRetornoPrevisto().isBefore(l.getSaidaPrevista()))
            throw new IllegalArgumentException("O retorno previsto não pode ser anterior à saída.");
        l.setDataSolicitacao(LocalDateTime.now());
        l.setStatus(StatusLiberacao.PENDENTE);
        return liberacoes.save(l);
    }
    @Transactional public void decidir(Long id, boolean aprovar){
        Liberacao l=buscar(id);
        if(l.getStatus()!=StatusLiberacao.PENDENTE) throw new IllegalStateException("Esta solicitação já foi analisada.");
        l.setStatus(aprovar?StatusLiberacao.APROVADA:StatusLiberacao.RECUSADA);
        liberacoes.save(l);
    }
    @Transactional public void registrarSaida(Long liberacaoId, Long porteiroId){
        Liberacao l=buscar(liberacaoId);
        if(l.getStatus()!=StatusLiberacao.APROVADA) throw new IllegalStateException("Somente liberações aprovadas podem registrar saída.");
        RegistroPortaria r=registros.findAll().stream().filter(x->x.getLiberacao().getId().equals(liberacaoId)).findFirst().orElseGet(RegistroPortaria::new);
        if(r.getSaidaReal()!=null) throw new IllegalStateException("A saída já foi registrada.");
        r.setLiberacao(l); r.setPorteiro(usuarios.findById(porteiroId).orElseThrow()); r.setSaidaReal(LocalDateTime.now());
        l.setStatus(StatusLiberacao.UTILIZADA); registros.save(r); liberacoes.save(l);
    }
    @Transactional public void registrarRetorno(Long liberacaoId){
        RegistroPortaria r=registros.findAll().stream().filter(x->x.getLiberacao().getId().equals(liberacaoId)).findFirst().orElseThrow(()->new IllegalArgumentException("Saída ainda não registrada."));
        if(r.getRetornoReal()!=null) throw new IllegalStateException("O retorno já foi registrado.");
        r.setRetornoReal(LocalDateTime.now()); registros.save(r);
    }
}

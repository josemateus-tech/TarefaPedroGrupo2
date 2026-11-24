package br.edu.ifsistema.sistema.model;

import br.edu.ifsistema.sistema.model.enums.StatusLiberacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="liberacoes")
public class Liberacao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="aluno_id")
    private Usuario aluno;
    @ManyToOne(optional=false) @JoinColumn(name="professor_id")
    private Usuario professor;
    @NotNull private LocalDateTime dataSolicitacao;
    @NotNull private LocalDateTime saidaPrevista;
    private LocalDateTime retornoPrevisto;
    @NotBlank @Column(length=1000) private String motivo;
    @Enumerated(EnumType.STRING) @NotNull private StatusLiberacao status = StatusLiberacao.PENDENTE;
    @Column(unique=true, nullable=false) private String tokenQr = UUID.randomUUID().toString();

    @PrePersist void antesDeSalvar(){if(dataSolicitacao==null)dataSolicitacao=LocalDateTime.now(); if(tokenQr==null)tokenQr=UUID.randomUUID().toString(); if(status==null)status=StatusLiberacao.PENDENTE;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Usuario getAluno(){return aluno;} public void setAluno(Usuario aluno){this.aluno=aluno;}
    public Usuario getProfessor(){return professor;} public void setProfessor(Usuario professor){this.professor=professor;}
    public LocalDateTime getDataSolicitacao(){return dataSolicitacao;} public void setDataSolicitacao(LocalDateTime v){dataSolicitacao=v;}
    public LocalDateTime getSaidaPrevista(){return saidaPrevista;} public void setSaidaPrevista(LocalDateTime v){saidaPrevista=v;}
    public LocalDateTime getRetornoPrevisto(){return retornoPrevisto;} public void setRetornoPrevisto(LocalDateTime v){retornoPrevisto=v;}
    public String getMotivo(){return motivo;} public void setMotivo(String v){motivo=v;}
    public StatusLiberacao getStatus(){return status;} public void setStatus(StatusLiberacao v){status=v;}
    public String getTokenQr(){return tokenQr;} public void setTokenQr(String v){tokenQr=v;}
}

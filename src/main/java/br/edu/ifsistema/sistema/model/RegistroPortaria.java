package br.edu.ifsistema.sistema.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="registros_portaria")
public class RegistroPortaria {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional=false) @JoinColumn(name="liberacao_id", unique=true)
    private Liberacao liberacao;
    @ManyToOne(optional=false) @JoinColumn(name="porteiro_id")
    private Usuario porteiro;
    private LocalDateTime saidaReal;
    private LocalDateTime retornoReal;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Liberacao getLiberacao(){return liberacao;} public void setLiberacao(Liberacao v){liberacao=v;}
    public Usuario getPorteiro(){return porteiro;} public void setPorteiro(Usuario v){porteiro=v;}
    public LocalDateTime getSaidaReal(){return saidaReal;} public void setSaidaReal(LocalDateTime v){saidaReal=v;}
    public LocalDateTime getRetornoReal(){return retornoReal;} public void setRetornoReal(LocalDateTime v){retornoReal=v;}
}

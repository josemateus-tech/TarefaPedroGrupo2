package br.edu.ifsistema.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "turmas")
public class Turma {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String nome;
    @NotBlank private String curso;
    private String periodo;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
    public String getCurso(){return curso;} public void setCurso(String curso){this.curso=curso;}
    public String getPeriodo(){return periodo;} public void setPeriodo(String periodo){this.periodo=periodo;}
    @Override public String toString(){return nome + " - " + curso;}
}

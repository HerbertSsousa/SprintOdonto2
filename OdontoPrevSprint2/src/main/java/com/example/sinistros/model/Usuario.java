package com.example.sinistros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 255)
    private String senha;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Foto> fotos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Erro> erros;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Processo> processos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;
}

package com.example.sinistros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

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

    private LocalDate dataCriacao;

    private List<FotoDTO> fotos;
    private List<ErroDTO> erros;
    private List<ProcessoDTO> processos;
    private List<NotificacaoDTO> notificacoes;
}

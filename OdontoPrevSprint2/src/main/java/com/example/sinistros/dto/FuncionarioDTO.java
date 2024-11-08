package com.example.sinistros.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String cargo;
    private Double salario;
    private Date dataAdmissao;
    private String telefone;
    private String email;
}

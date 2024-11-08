package com.example.sinistros.service;

import com.example.sinistros.dto.*;
import com.example.sinistros.model.*;
import com.example.sinistros.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para converter Usuario para UsuarioDTO
    private UsuarioDTO converterParaDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getIdUser(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getDataCriacao(),
                converterFotosParaDTO(usuario.getFotos()),
                converterErrosParaDTO(usuario.getErros()),
                converterProcessosParaDTO(usuario.getProcessos()),
                converterNotificacoesParaDTO(usuario.getNotificacoes())
        );
    }

    private List<FotoDTO> converterFotosParaDTO(List<Foto> fotos) {
        return fotos != null ? fotos.stream().map(FotoDTO::new).collect(Collectors.toList()) : List.of();
    }

    private List<ErroDTO> converterErrosParaDTO(List<Erro> erros) {
        return erros != null ? erros.stream().map(ErroDTO::new).collect(Collectors.toList()) : List.of();
    }

    private List<ProcessoDTO> converterProcessosParaDTO(List<Processo> processos) {
        //return processos == null ? List.of() : processos.stream().map(ProcessoDTO::new).collect(Collectors.toList());

        if (processos == null) {
            return null;
        }

        List<ProcessoDTO> listProcessos = new ArrayList<>();;

        for (Processo processo : processos) {
            listProcessos.add(new ProcessoDTO(
                    processo.getIdProcesso(),
                    99,//processo.getIdUsuario(),
                    processo.getAnaliseId(),
                    processo.getDataAnalise()
            ));
        }

        return listProcessos;
    }

    private List<NotificacaoDTO> converterNotificacoesParaDTO(List<Notificacao> notificacoes) {
        return notificacoes != null ? notificacoes.stream().map(NotificacaoDTO::new).collect(Collectors.toList()) : List.of();
    }

    // Método para converter UsuarioDTO para Usuario
    private Usuario converterParaEntidade(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setIdUser(usuarioDTO.getIdUser());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setDataCriacao(usuarioDTO.getDataCriacao());

        // Aqui você deve implementar a lógica para converter as listas de DTO para entidades
        // Exemplo:
        // usuario.setFotos(converterParaEntidades(usuarioDTO.getFotos()));
        // usuario.setErros(converterParaEntidades(usuarioDTO.getErros()));
        // usuario.setProcessos(converterParaEntidades(usuarioDTO.getProcessos()));
        // usuario.setNotificacoes(converterParaEntidades(usuarioDTO.getNotificacoes()));

        return usuario;
    }

    public List<UsuarioDTO> listarUsuarios() {
        var teste = usuarioRepository.findAll();
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).map(this::converterParaDTO);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterParaEntidade(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return converterParaDTO(usuarioSalvo);
    }

    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuarioDTO.setIdUser(id);
        Usuario usuario = converterParaEntidade(usuarioDTO);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return converterParaDTO(usuarioAtualizado);
    }

    public void deletarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}

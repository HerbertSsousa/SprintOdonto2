package com.example.sinistros.repository;


import com.example.sinistros.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
    // Exemplo: Optional<Usuario> findByCpf(String cpf);
}

package br.com.branetlogistica.ms_transferencia.modulos.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.ms_transferencia.modulos.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

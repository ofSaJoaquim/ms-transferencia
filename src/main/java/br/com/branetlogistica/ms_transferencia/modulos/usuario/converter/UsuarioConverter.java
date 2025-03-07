package br.com.branetlogistica.ms_transferencia.modulos.usuario.converter;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.usuario.dto.UsuarioDTO;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.dto.UsuarioQueue;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.dto.UsuarioResponse;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.model.Usuario;

@Component
public class UsuarioConverter {

	
	public Usuario toEntity(UsuarioQueue queue) {
		return Usuario.builder()
				.id(queue.getId())
				.disabled(false)
				.version(Long.parseLong(queue.getVersionNumber()))
				.login(queue.getLogin())
				.email(queue.getEmail())
				.build();
		
	}
	
	
	public UsuarioResponse toResponse(Usuario entidade) {
		return UsuarioResponse.builder()
				.id(entidade.getId())
				.login(entidade.getLogin())
				.email(entidade.getEmail())				
				.build();
	}
	
	public UsuarioDTO toDto(Usuario entity) {
		if(entity == null)
			return null;
		UsuarioDTO dto = new UsuarioDTO(entity.getId(),entity.getLogin());
		return dto;
	}
	
}

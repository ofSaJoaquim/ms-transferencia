package br.com.branetlogistica.ms_transferencia.modulos.usuario.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioQueue implements IQueueDto {

	private Long id;
	private String login;
	private String email;	
	private String versionNumber;
	
}

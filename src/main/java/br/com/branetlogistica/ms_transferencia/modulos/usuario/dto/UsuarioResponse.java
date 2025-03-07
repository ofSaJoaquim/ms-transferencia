package br.com.branetlogistica.ms_transferencia.modulos.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

	private Long id;
	private String login;
	private String email;
	
}

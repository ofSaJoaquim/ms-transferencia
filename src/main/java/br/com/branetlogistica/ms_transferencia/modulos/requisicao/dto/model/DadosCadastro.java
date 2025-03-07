package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.branetlogistica.ms_transferencia.modulos.usuario.dto.UsuarioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DadosCadastro {

	private String dataHora;
	private String observacao;
	private UsuarioDTO usuario;
	
}

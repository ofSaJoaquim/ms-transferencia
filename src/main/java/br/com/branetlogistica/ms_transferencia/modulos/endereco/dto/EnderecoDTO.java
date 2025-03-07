package br.com.branetlogistica.ms_transferencia.modulos.endereco.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class EnderecoDTO {

	private Long id;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private String cep;
	private Long cidadeId;
	private String cidadeNome;
	private Long estadoId;
	private String estadoNome;
	private String estadoUf;
	private boolean semNumero;

}

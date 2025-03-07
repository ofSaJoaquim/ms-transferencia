package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MercadoriaResponse {

	private Long id;
	private String version;
	
	private String nome;
	private String numeroCATMAT;
	
	
	
}

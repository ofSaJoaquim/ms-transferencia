package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto;

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
public class LoteResponse {

	private Long id;
	private String version;
	
	private String nome;
	private String validade;
	private Long idCodigoBarras;
	
	
}

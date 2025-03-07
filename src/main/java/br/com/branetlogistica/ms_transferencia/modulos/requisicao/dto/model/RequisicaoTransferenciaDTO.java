package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RequisicaoTransferenciaDTO {

	private Long id;
	private String status;
	private String dataHoraFinalizado;
	
	
	
	
	 
}

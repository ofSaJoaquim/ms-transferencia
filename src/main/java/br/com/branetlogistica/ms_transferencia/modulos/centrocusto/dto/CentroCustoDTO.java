package br.com.branetlogistica.ms_transferencia.modulos.centrocusto.dto;

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
public class CentroCustoDTO {

	private Long id;
	private String nome;
	private Long unidadeId;
	private String unidadeNome;
	private String unidadeCnes;
	
}

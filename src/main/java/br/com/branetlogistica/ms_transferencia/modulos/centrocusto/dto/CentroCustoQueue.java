package br.com.branetlogistica.ms_transferencia.modulos.centrocusto.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentroCustoQueue implements IQueueDto {
	private Long id;
	private Long centroDistribuicaoId;
	private String versionNumber;	
	private String nome;
	private Boolean usaEstoque;
	private boolean usaWMS;
	
}

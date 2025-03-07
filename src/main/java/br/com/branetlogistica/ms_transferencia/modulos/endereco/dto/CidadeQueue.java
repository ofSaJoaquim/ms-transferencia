package br.com.branetlogistica.ms_transferencia.modulos.endereco.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CidadeQueue implements IQueueDto {

	private Long id;	
	private String nome;
	private Long estadoId;
	private Long ibge;
	private String versionNumber;
	
}

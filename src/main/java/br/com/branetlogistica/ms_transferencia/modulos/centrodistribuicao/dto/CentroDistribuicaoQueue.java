package br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentroDistribuicaoQueue implements IQueueDto{

	private Long id;
	private String versionNumber;
	private String nome;
	private String cnes;
	private Long pessoaJuridicaId;
	private Long enderecoId;
	
	private String tipoEstabelecimento; 
}

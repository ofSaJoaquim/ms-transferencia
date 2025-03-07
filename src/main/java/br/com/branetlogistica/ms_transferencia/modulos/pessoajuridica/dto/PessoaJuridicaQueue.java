package br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PessoaJuridicaQueue implements IQueueDto {

	private Long id;
	private String versionNumber;	
	private String razaoSocial;	
	private String nomeFantasia;	
	private String cnpj;
	
}

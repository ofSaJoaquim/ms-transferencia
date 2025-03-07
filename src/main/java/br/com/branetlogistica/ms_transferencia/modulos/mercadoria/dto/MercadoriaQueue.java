package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MercadoriaQueue implements IQueueDto {

	private Long id;
	private String versionNumber;	
	
	private String nome;	
	private String numeroRegistroAnvisa;	
	private boolean exportaHorus;	
	private String numeroCatmat;
	private String tipoProduto;	
	
}

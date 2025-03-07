package br.com.branetlogistica.ms_transferencia.modulos.endereco.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
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
public class EnderecoQueue implements IQueueDto {
	private Long id;
	private String tipoLogradouro;	
	private String logradouro;	
	private String numero;	
	private String bairro;	
	private String complemento;	
	private String cep;	
	private Long cidadeId;	
	private boolean semNumero;
	private String versionNumber;
	
}

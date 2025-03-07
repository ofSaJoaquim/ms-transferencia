package br.com.branetlogistica.ms_transferencia.modulos.anexo.dto;

import br.com.branetlogistica.ms_transferencia.modulos.anexo.enums.ArquivoFormatoEnum;
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
public class ArquivoAnexoResponse {

	private Long id;
	private String dadosCodificado;	
	private String nome;	
	private ArquivoFormatoEnum formato;
	private String version;
}

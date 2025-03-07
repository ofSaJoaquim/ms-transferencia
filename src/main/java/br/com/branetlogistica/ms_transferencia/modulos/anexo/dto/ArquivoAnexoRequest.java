package br.com.branetlogistica.ms_transferencia.modulos.anexo.dto;

import br.com.branetlogistica.ms_transferencia.modulos.anexo.enums.ArquivoFormatoEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ArquivoAnexoRequest {

	@NotNull
	@NotEmpty
	private String dadosCodificado;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	private ArquivoFormatoEnum formato;
	
}

package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequisicaoTransferenciaItemCadastroRequest {

	@NotNull
	@Min(1l)
	private Long requisicaoTransferenciaId;
		
	@NotNull
	@Min(1l)
	private Long usuarioCadastroId;
	
	@NotNull
	@Min(1l)
	private Long mercadoriaId;;
	
	
	private Long fabricanteId;
	
	@NotNull
	@Min(1)
	private Integer quantidade;
	
	@NotNull
	@NotBlank
	private String lote;
	
	
	private String validade;
	
	
	
}

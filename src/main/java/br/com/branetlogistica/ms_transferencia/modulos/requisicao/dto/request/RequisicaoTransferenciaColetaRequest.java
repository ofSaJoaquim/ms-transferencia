package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequisicaoTransferenciaColetaRequest {
	
	@NotNull
	@Min(1l)
	private Long usuarioColetaId;
	
	@Length(max = 1024)
	private String observacaoColeta;
	
}

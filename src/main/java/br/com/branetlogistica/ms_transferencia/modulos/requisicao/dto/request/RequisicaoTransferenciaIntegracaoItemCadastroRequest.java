package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequisicaoTransferenciaIntegracaoItemCadastroRequest {


	private Long mercadoriaId;;
	
	private Long fabricanteId;
	private String fabricanteCnpj;
	
	private Integer quantidade;
	private String lote;
	private String validade;
	
	
	
}

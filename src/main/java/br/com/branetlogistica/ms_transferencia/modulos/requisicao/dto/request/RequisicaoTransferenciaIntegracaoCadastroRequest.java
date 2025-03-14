package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequisicaoTransferenciaIntegracaoCadastroRequest {
	
	private Long centroCustoOrigemId;
	private Long centroCustoDestinoId;
		
	private Long usuarioCadastroId;
	private String usuarioCadastroCpf;
			
	private String observacaoCadastro;	
	
	private List<RequisicaoTransferenciaIntegracaoItemCadastroRequest> itens;
}

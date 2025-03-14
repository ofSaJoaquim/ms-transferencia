package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.dto.CentroCustoDTO;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.DadosCadastro;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.RequisicaoTransferenciaItemDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RequisicaoTransferenciaResponse {

	private Long id;
	private String status;
	private String dataHoraFinalizado;
	
	private CentroCustoDTO centroCustoOrigem;
	private CentroCustoDTO centroCustoDestino;
	
	private DadosCadastro dadosCadastro;
	private DadosCadastro dadosAutorizacao;
	private DadosCadastro dadosAprovacao;
	private DadosCadastro dadosInativacao;
	private DadosCadastro dadosColeta;
	private DadosCadastro dadosRecebimento;
	private DadosCadastro dadosRejeicao;
	
	private List<RequisicaoTransferenciaItemResponse> itens;	
}

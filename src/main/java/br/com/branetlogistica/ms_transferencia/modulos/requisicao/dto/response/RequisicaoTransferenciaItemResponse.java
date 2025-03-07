package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.MercadoriaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.dto.PessoaJuridicaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.IRequisicaoTransferenciaItemDTO;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.DadosCadastro;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.RequisicaoTransferenciaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RequisicaoTransferenciaItemResponse implements IRequisicaoTransferenciaItemDTO{

	private Long id;
	private String status;
	private String lote;
	private String validade;	
	private Integer quantidade;
	private Integer quantidadeRecebida;
	
	
	private RequisicaoTransferenciaDTO requisicaoTransferencia;
	
	private MercadoriaResponse mercadoria;
	private PessoaJuridicaResponse fabricante;
	
	private DadosCadastro dadosCadastro;
	private DadosCadastro dadosRejeicao;
	
}
